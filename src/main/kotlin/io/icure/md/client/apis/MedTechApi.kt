package io.icure.md.client.apis

import io.icure.kraken.client.apis.CodeApi
import io.icure.kraken.client.apis.ContactApi
import io.icure.kraken.client.apis.DeviceApi
import io.icure.kraken.client.apis.HcpartyApi
import io.icure.kraken.client.apis.HealthElementApi
import io.icure.kraken.client.apis.PatientApi
import io.icure.kraken.client.apis.UserApi
import io.icure.kraken.client.crypto.LocalCrypto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
class MedTechApi(
    iCureUrlPath: String,
    authorization: String,
    rsaKeyPairs: MutableMap<String, Pair<RSAPrivateKey, RSAPublicKey>>
) {
    private val userApi = UserApi(basePath = iCureUrlPath, authHeader = authorization)
    private val patientApi = PatientApi(basePath = iCureUrlPath, authHeader = authorization)
    private val deviceApi = DeviceApi(basePath = iCureUrlPath, authHeader = authorization)
    private val healthElementApi = HealthElementApi(basePath = iCureUrlPath, authHeader = authorization)
    private val contactApi = ContactApi(basePath = iCureUrlPath, authHeader = authorization)
    private val codeApi = CodeApi(basePath = iCureUrlPath, authHeader = authorization)
    private val hcPartyApi = HcpartyApi(basePath = iCureUrlPath, authHeader = authorization)
    private val localCrypto = LocalCrypto(hcPartyApi, rsaKeyPairs)

    fun localCrypto() = localCrypto

    fun userApi() = userApi

    fun patientApi() = patientApi

    fun deviceApi() = deviceApi

    fun healthElementApi() = healthElementApi

    fun contactApi() = contactApi

    fun codeApi() = codeApi

    fun hcPartyApi() = hcPartyApi

    data class Builder(
        private var iCureUrlPath: String = defaultBasePath,
        private var authorization: String? = null,
        private var rsaKeyPairs: MutableMap<String, Pair<RSAPrivateKey, RSAPublicKey>> = mutableMapOf()
    ) {
        fun iCureUrlPath(iCureUrlPath: String) = apply { this.iCureUrlPath = iCureUrlPath }
        fun authorization(authorization: String) = apply { this.authorization = authorization }
        fun addKeyPair(keyId: String, publicKey: RSAPublicKey, privateKey: RSAPrivateKey) =
            apply { this.rsaKeyPairs[keyId] = privateKey to publicKey }

        fun build(): MedTechApi {
            if (authorization == null) {
                throw IllegalArgumentException("In order to request iCure APIs, you need to provide your credentials")
            }

            if (rsaKeyPairs.isEmpty()) {
                throw IllegalArgumentException("In order to encrypt/decrypt your data, you need to provide at least a RSA Key Pair")
            }

            return MedTechApi(iCureUrlPath, authorization!!, rsaKeyPairs)
        }
    }

    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty("io.icure.kraken.client.baseUrl", "https://kraken.icure.dev")
        }
    }
}