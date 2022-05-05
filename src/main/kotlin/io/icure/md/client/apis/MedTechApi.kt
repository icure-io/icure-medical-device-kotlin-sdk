package io.icure.md.client.apis

import io.icure.kraken.client.apis.CodeApi
import io.icure.kraken.client.apis.ContactApi
import io.icure.kraken.client.apis.DeviceApi
import io.icure.kraken.client.apis.DocumentApi
import io.icure.kraken.client.apis.HealthElementApi
import io.icure.kraken.client.apis.HealthcarePartyApi
import io.icure.kraken.client.apis.PatientApi
import io.icure.kraken.client.apis.UserApi
import io.icure.kraken.client.crypto.LocalCrypto
import io.icure.kraken.client.extendedapis.DataOwnerApi
import io.icure.kraken.client.extendedapis.DataOwnerResolver
import io.icure.md.client.apis.impl.AuthenticationApiImpl
import io.icure.md.client.apis.impl.CodingApiImpl
import io.icure.md.client.apis.impl.DataSampleApiImpl
import io.icure.md.client.apis.impl.HealthcareElementApiImpl
import io.icure.md.client.apis.impl.HealthcareProfessionalApiImpl
import io.icure.md.client.apis.impl.MedicalDeviceApiImpl
import io.icure.md.client.apis.impl.PatientApiImpl
import io.icure.md.client.apis.impl.UserApiImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalUnsignedTypes
@ExperimentalTime
class MedTechApi(
    internal val iCureUrlPath: String,
    internal val authorization: String,
    internal val rsaKeyPairs: MutableMap<String, Pair<RSAPrivateKey, RSAPublicKey>>,
    internal val defaultLanguage: String,
    internal val authServerUrl: String?,
    internal val authProcessId: String?,
    internal val shortLivedCachesDuration: Long,
    internal val shortLivedCachesMaxSize: Long,
    internal val baseUserApi: UserApi = UserApi(basePath = iCureUrlPath, authHeader = authorization),
    internal val basePatientApi: PatientApi = PatientApi(basePath = iCureUrlPath, authHeader = authorization),
    internal val baseDeviceApi: DeviceApi = DeviceApi(basePath = iCureUrlPath, authHeader = authorization),
    internal val baseHealthElementApi: HealthElementApi = HealthElementApi(
        basePath = iCureUrlPath,
        authHeader = authorization
    ),
    internal val baseContactApi: ContactApi = ContactApi(basePath = iCureUrlPath, authHeader = authorization),
    internal val baseCodeApi: CodeApi = CodeApi(basePath = iCureUrlPath, authHeader = authorization),
    internal val baseHcpApi: HealthcarePartyApi = HealthcarePartyApi(
        basePath = iCureUrlPath,
        authHeader = authorization
    ),
    internal val baseDocumentApi: DocumentApi = DocumentApi(basePath = iCureUrlPath, authHeader = authorization),
    internal val localCrypto: LocalCrypto = LocalCrypto(
        DataOwnerResolver(baseHcpApi, basePatientApi, baseDeviceApi),
        rsaKeyPairs
    ),
    internal val baseDataOwnerApi: DataOwnerApi = DataOwnerApi(
        healthcarePartyApi = baseHcpApi,
        patientApi = basePatientApi,
        deviceApi = baseDeviceApi
    )
) {
    private val authenticationApi by lazy {
        if (this.authServerUrl == null || this.authProcessId == null) {
            throw IllegalArgumentException("To use AuthenticationApi, you need to provide the msgGtwUrl, your authServerUrl and your authProcessId!")
        }

        AuthenticationApiImpl(
            iCureUrlPath = iCureUrlPath,
            authServerUrl = authServerUrl,
            authProcessId = authProcessId,
            defaultLanguage = defaultLanguage
        )
    }
    private val codingApi by lazy { CodingApiImpl(this) }
    private val dataSampleApi by lazy { DataSampleApiImpl(this) }
    private val healthcareElementApi by lazy { HealthcareElementApiImpl(this) }
    private val healthcareProfessionalApi by lazy { HealthcareProfessionalApiImpl(this) }
    private val medicalDeviceApi by lazy { MedicalDeviceApiImpl(this) }
    private val patientApi by lazy { PatientApiImpl(this) }
    private val userApi by lazy { UserApiImpl(this) }

    fun authenticationApi(): AuthenticationApi = authenticationApi
    fun codingApi() = codingApi
    fun dataSampleApi() = dataSampleApi
    fun healthcareElementApi() = healthcareElementApi
    fun healthcareProfessionalApi() = healthcareProfessionalApi
    fun medicalDeviceApi() = medicalDeviceApi
    fun patientApi() = patientApi
    fun userApi() = userApi

    data class Builder(
        private var iCureUrlPath: String = defaultBasePath,
        private var userName: String? = null,
        private var password: String? = null,
        private var authorization: String? = null,
        private var rsaKeyPairs: MutableMap<String, Pair<RSAPrivateKey, RSAPublicKey>> = mutableMapOf(),
        private var authServerUrl: String? = null,
        private var authProcessId: String? = null,
        private var defaultLanguage: String = "en",
        private var shortLivedCachesDuration: Long = 5 * 60,
        private var shortLivedCachesMaxSize: Long = 1000
    ) {
        fun defaultLanguage(language: String) = apply { this.defaultLanguage = language }
        fun shortLivedCacheDurationInSeconds(seconds: Long) = apply { this.shortLivedCachesDuration = seconds }
        fun shortLivedCacheMaxSize(maxSize: Long) = apply { this.shortLivedCachesMaxSize = maxSize }

        fun iCureUrlPath(iCureUrlPath: String) = apply { this.iCureUrlPath = iCureUrlPath }
        fun userName(username: String) = apply { this.userName = username }
        fun password(password: String) = apply { this.password = password }
        fun authorization(authorization: String) = apply { this.authorization = authorization }
        fun authServerUrl(authServerUrl: String?) = apply { this.authServerUrl = authServerUrl }
        fun authProcessId(authProcessId: String?) = apply { this.authProcessId = authProcessId }
        fun addKeyPair(keyId: String, publicKey: RSAPublicKey, privateKey: RSAPrivateKey) =
            apply { this.rsaKeyPairs[keyId] = privateKey to publicKey }

        private fun basicAuth(userName: String, password: String) =
            "Basic ${java.util.Base64.getEncoder().encodeToString("$userName:$password".toByteArray())}"

        fun build(): MedTechApi {
            if (userName != null && password != null) {
                authorization = basicAuth(userName = userName!!, password = password!!)
            }

            if (authorization == null) {
                throw IllegalArgumentException("In order to request iCure APIs, you need to provide your credentials")
            }

            return MedTechApi(
                iCureUrlPath = iCureUrlPath,
                authorization = authorization!!,
                rsaKeyPairs = rsaKeyPairs,
                defaultLanguage = defaultLanguage,
                shortLivedCachesDuration = shortLivedCachesDuration,
                shortLivedCachesMaxSize = shortLivedCachesMaxSize,
                authServerUrl = authServerUrl,
                authProcessId = authProcessId
            )
        }

        companion object {
            fun from(api: MedTechApi): Builder {
                return Builder()
                    .authProcessId(api.authProcessId)
                    .authServerUrl(api.authServerUrl)
                    .authorization(api.authorization)
                    .defaultLanguage(api.defaultLanguage)
                    .authorization(api.authorization)
                    .shortLivedCacheDurationInSeconds(api.shortLivedCachesDuration)
                    .shortLivedCacheMaxSize(api.shortLivedCachesMaxSize)
                    .authorization(api.authorization)
            }
        }
    }

    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty("io.icure.kraken.client.baseUrl", "https://kraken.icure.dev")
        }
    }
}
