package io.icure.md.client.apis.impl

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.json.JsonReadFeature
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.crypto.toPrivateKey
import io.icure.kraken.client.crypto.toPublicKey
import io.icure.kraken.client.extendedapis.DataOwnerApi
import io.icure.kraken.client.extendedapis.dataOwnerId
import io.icure.kraken.client.extendedapis.initDelegations
import io.icure.kraken.client.extendedapis.modifyPatient
import io.icure.kraken.client.models.DeviceDto
import io.icure.kraken.client.models.HealthcarePartyDto
import io.icure.kraken.client.models.PatientDto
import io.icure.kraken.client.models.UserDto
import io.icure.md.client.apis.AuthenticationApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.mappers.toDecryptedPatientDto
import io.icure.md.client.models.ApiInitialisationResult
import io.icure.md.client.models.AuthenticationProcess
import io.icure.md.client.models.AuthenticationResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.reactive.awaitFirstOrNull
import reactor.core.publisher.Mono
import reactor.netty.ByteBufFlux
import reactor.netty.http.client.HttpClient
import java.io.IOException
import java.net.URLEncoder
import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration
import io.icure.kraken.client.models.decrypted.PatientDto as DecryptedPatientDto

@ExperimentalStdlibApi
@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalTime
class AuthenticationApiImpl(
    private val iCureUrlPath: String,
    private val authServerUrl: String,
    private val authProcessId: String,
    private val defaultLanguage: String
) : AuthenticationApi {

    companion object {
        fun fromApi(api: MedTechApi) = AuthenticationApiImpl(
            iCureUrlPath = api.iCureUrlPath,
            authServerUrl = api.authServerUrl!!,
            authProcessId = api.authProcessId!!,
            defaultLanguage = api.defaultLanguage
        )
    }

    private val objectMapper: ObjectMapper = ObjectMapper()
        .registerModule(KotlinModule())
        .registerModule(JavaTimeModule()).apply {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
        }

    private fun client() = HttpClient
        .create()
        .headers { h ->
            h.set("Content-Type", "application/json")
        }

    override suspend fun startAuthentication(
        healthcareProfessionalId: String,
        dataOwnerId: String,
        firstName: String,
        lastName: String,
        email: String,
        recaptcha: String,
        mobilePhone: String?
    ): AuthenticationProcess? {
        val requestId: String = UUID.randomUUID().toString()

        val params = mapOf(
            "g-recaptcha-response" to recaptcha,
            "firstName" to firstName,
            "lastName" to lastName,
            "from" to email,
            "mobilePhone" to mobilePhone,
            "hcpId" to healthcareProfessionalId
        )

        val response = client().post()
            .uri(
                URLEncoder.encode(
                    "${authServerUrl}/process/${authProcessId}/$requestId",
                    Charsets.UTF_8
                )
            )
            .send(ByteBufFlux.fromString(Mono.just(objectMapper.writeValueAsString(params))))
            .response()
            .awaitFirstOrNull()

        return response?.takeIf { it.status().code() < 400 }?.let { AuthenticationProcess(requestId, email) }
    }

    override suspend fun completeAuthentication(
        process: AuthenticationProcess,
        validationCode: String,
        patientKeyPair: Pair<String, String>,
        tokenAndKeyPairProvider: (String, String) -> Triple<String, String, String>?
    ): AuthenticationResult {
        val response = client().get()
            .uri(
                URLEncoder.encode(
                    "${authServerUrl}/process/validate/${process.processId}-$validationCode",
                    Charsets.UTF_8
                )
            )
            .response()
            .awaitFirstOrNull()

        if (response!!.status().code() < 400) {
            return flow {
                val (api, initResult) = initApiAndUserAuthenticationToken(
                    process,
                    validationCode,
                    tokenAndKeyPairProvider
                )
                val keyPair = initResult.keyPair ?: patientKeyPair
                val authenticatedApi = initUserCrypto(api, initResult.token, initResult.userDto, keyPair)

                emit(
                    AuthenticationResult(
                        authenticatedApi,
                        keyPair,
                        initResult.token,
                        initResult.userDto.groupId!!,
                        initResult.userDto.id
                    )
                )
            }.retry(5) { exception ->
                (exception is IOException).also { if (it) delay(1000) }
            }.first()
        }
        throw IllegalArgumentException("Invalid validation code")
    }

    override suspend fun initApiAndUserAuthenticationToken(
        process: AuthenticationProcess,
        validationCode: String,
        tokenAndKeyPairProvider: (String, String) -> Triple<String, String, String>?
    ): Pair<MedTechApi, ApiInitialisationResult> {
        val api = MedTechApi.Builder()
            .iCureUrlPath(iCureUrlPath)
            .authProcessId(authProcessId)
            .authServerUrl(authServerUrl)
            .userName(process.login)
            .password(validationCode)
            .defaultLanguage(defaultLanguage)
            .build()

        try {
            val currentUser = api.baseUserApi.getCurrentUser()
            val fromProvider = tokenAndKeyPairProvider(currentUser.groupId!!, currentUser.id)
            val token =
                fromProvider?.first ?: api.userApi().createToken(currentUser.id, 30.toDuration(DurationUnit.DAYS))
            return api to ApiInitialisationResult(currentUser, token, fromProvider?.let { it.second to it.third })
        } catch (e: Exception) {
            throw IllegalArgumentException("Your validation code is expired")
        }
    }

    override suspend fun initUserCrypto(
        api: MedTechApi,
        token: String,
        user: UserDto,
        patientKeyPair: Pair<String, String>
    ): MedTechApi {
        val authenticatedApi = MedTechApi.Builder.from(api, "${user.groupId}/${user.id}", token)
            .addKeyPair(user.dataOwnerId(), patientKeyPair.second.toPublicKey(), patientKeyPair.second.toPrivateKey())
            .build()

        val dataOwnerApi = api.baseDataOwnerApi

        dataOwnerApi.getDataOwner<HealthcarePartyDto>(user.dataOwnerId())?.let {
            if (it.publicKey == null) {
                dataOwnerApi.modifyDataOwner(it.copy(publicKey = patientKeyPair.second))
            } else {
                TODO("HCP User lost his key")
            }
        } ?: dataOwnerApi.getDataOwner<PatientDto>(user.dataOwnerId())?.let {
            if (it.publicKey == null) {
                val updatedPatient = dataOwnerApi.modifyDataOwner(it.copy(publicKey = patientKeyPair.second))
                initPatientDelegationAndSave(
                    authenticatedApi,
                    updatedPatient.toDecryptedPatientDto(),
                    user,
                    dataOwnerApi
                )
            } else {
                TODO("Patient User lost his key")
            }
        } ?: dataOwnerApi.getDataOwner<DeviceDto>(user.dataOwnerId())?.let {
            if (it.publicKey == null) {
                dataOwnerApi.modifyDataOwner(it.copy(publicKey = patientKeyPair.second))
            } else {
                TODO("Device User lost his key")
            }
        } ?: throw IllegalStateException("User isn't a DataOwner")

        return authenticatedApi
    }

    override suspend fun initPatientDelegationAndSave(
        apiWithNewKeyPair: MedTechApi,
        modPat: DecryptedPatientDto,
        user: UserDto,
        dataOwnerApi: DataOwnerApi
    ): DecryptedPatientDto {
        val ccPatient = patientCryptoConfig(apiWithNewKeyPair.localCrypto)
        val dataOwnerWithDelegations = modPat.initDelegations(user, ccPatient)
        return apiWithNewKeyPair.basePatientApi.modifyPatient(user, dataOwnerWithDelegations, ccPatient)
    }
}
