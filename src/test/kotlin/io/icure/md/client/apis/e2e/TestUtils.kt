@file:OptIn(ExperimentalTime::class, FlowPreview::class)

package io.icure.md.client.apis.e2e

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.json.JsonReadFeature
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import io.icure.kraken.client.apis.HealthcarePartyApi
import io.icure.kraken.client.apis.PatientApi
import io.icure.kraken.client.crypto.toPrivateKey
import io.icure.kraken.client.crypto.toPublicKey
import io.icure.md.client.apis.MedTechApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import java.io.File
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
@ExperimentalUnsignedTypes
@FlowPreview
object TestUtils {

    private const val defaultHcpId = "782f1bcd-9f3f-408a-af1b-cd9f3f908a98"
    private val defaultObjectMapper: ObjectMapper = ObjectMapper()
        .registerModule(KotlinModule())
        .registerModule(JavaTimeModule()).apply {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
        }

    fun healthcareProfessionalPrivateKey(
        hcpId: String = defaultHcpId,
        clazz: Class<*> = this::class.java
    ): RSAPrivateKey = clazz.classLoader
        .getResource("io/icure/md/client/apis/impl/keys/$hcpId-icc-priv.2048.key")
        ?.readText()?.trim()?.toPrivateKey()
        ?: throw RuntimeException("Couldn't read HCP Key from file")

    suspend fun healthcareProfessionalPublicKey(
        basePath: String,
        authorization: String,
        hcpId: String
    ): RSAPublicKey {
        return HealthcarePartyApi(basePath = basePath, authHeader = authorization)
            .getPublicKey(hcpId)
            .hexString
            ?.toPublicKey()
            ?: throw RuntimeException("Couldn't read HCP Public Key")
    }

    private suspend fun patientPublicKey(
        basePath: String,
        authorization: String,
        patId: String
    ): RSAPublicKey {
        return PatientApi(basePath = basePath, authHeader = authorization)
            .getPatient(patId)
            .publicKey
            ?.toPublicKey()
            ?: throw RuntimeException("Couldn't read HCP Public Key")
    }

    fun basicAuthFrom(
        username: String = System.getenv("TEST_HCP_USERNAME"),
        password: String = System.getenv("TEST_HCP_PASSWORD")
    ): String {
        return UsernamePassword(username, password).toBasicAuth()
    }

    fun simpleApiBasedOn(authorization: String): MedTechApi {
        return MedTechApi.Builder()
            .iCureUrlPath(System.getenv("TEST_ICURE_URL") ?: "https://kraken.icure.dev")
            .authorization(authorization)
            .build()
    }

    fun hcpApiBasedOn(
        creds: String = basicAuthFrom(
            System.getenv("TEST_HCP_USERNAME"),
            System.getenv("TEST_HCP_PASSWORD")
        ),
        healthcareProfessionalId: String = System.getenv("TEST_HCP_ID"),
        healthcareProfessionalPrivateKey: String = System.getenv("TEST_HCP_PRIV_KEY")
    ): MedTechApi {
        val iCurePath = System.getenv("TEST_ICURE_URL") ?: "https://kraken.icure.dev"
        val healthcareProfessionalPublicKey =
            runBlocking { healthcareProfessionalPublicKey(iCurePath, creds, healthcareProfessionalId) }

        return MedTechApi.Builder()
            .iCureUrlPath(iCurePath)
            .authorization(creds)
            .addKeyPair(
                healthcareProfessionalId,
                healthcareProfessionalPublicKey,
                healthcareProfessionalPrivateKey.toPrivateKey()
            )
            .build()
    }

    fun patApiBasedOn(
        creds: String = basicAuthFrom(
            System.getenv("TEST_PAT_USERNAME"),
            System.getenv("TEST_PAT_PASSWORD")
        ), patId: String = System.getenv("TEST_PAT_ID"), patPrivateKey: String = System.getenv("TEST_PAT_PRIV_KEY")
    ): MedTechApi {
        val iCurePath = System.getenv("TEST_ICURE_URL") ?: "https://kraken.icure.dev"
        val patPublicKey =
            runBlocking { patientPublicKey(iCurePath, creds, patId) }

        return MedTechApi.Builder()
            .iCureUrlPath(iCurePath)
            .authorization(creds)
            .addKeyPair(patId, patPublicKey, patPrivateKey.toPrivateKey())
            .build()
    }

    data class UsernamePassword(val username: String, val password: String) {
        fun toBasicAuth() = "Basic ${java.util.Base64.getEncoder().encodeToString("$username:$password".toByteArray())}"
    }

    @JsonIgnoreProperties(value = ["api"])
    data class UserCredentials(
        val userName: String,
        val token: String,
        val dataOwnerId: String,
        val publicKey: String,
        val privateKey: String,
    ) {
        val api: MedTechApi by lazy {
            MedTechApi.Builder()
                .authProcessId(System.getenv("PAT_AUTH_PROCESS_ID") ?: "6a355458dbfa392cb5624403190c39e5")
                .authServerUrl(System.getenv("TEST_AUTH_SERVER_URL") ?: "https://msg-gw.icure.cloud/km")
                .iCureUrlPath(System.getenv("TEST_ICURE_URL") ?: "https://kraken.icure.dev")
                .userName(userName)
                .password(token)
                .addKeyPair(dataOwnerId, publicKey.toPublicKey(), privateKey.toPrivateKey())
                .build()
        }

        companion object {
            fun fromFile(fileName: String): UserCredentials {
                return defaultObjectMapper.readValue(
                    this::class.java.classLoader.getResource("io/icure/md/client/apis/impl/credentials/$fileName")
                        .readText()
                )!!
            }

            fun fromDir(dirPath: String = "io/icure/md/client/apis/impl/credentials/"): List<UserCredentials> {
                return File(this::class.java.classLoader.getResource(dirPath).toURI()).walkBottomUp()
                    .mapNotNull { file ->
                        file.takeIf { it.isFile }?.let {
                            defaultObjectMapper.readValue<UserCredentials>(it.readText(Charsets.UTF_8))
                        }
                    }.toList()
            }
        }
    }

}
