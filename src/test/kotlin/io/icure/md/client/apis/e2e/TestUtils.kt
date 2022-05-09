@file:OptIn(ExperimentalTime::class, FlowPreview::class)

package io.icure.md.client.apis.e2e

import io.icure.kraken.client.apis.HealthcarePartyApi
import io.icure.kraken.client.apis.PatientApi
import io.icure.kraken.client.crypto.toPrivateKey
import io.icure.kraken.client.crypto.toPublicKey
import io.icure.md.client.apis.MedTechApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import java.security.interfaces.RSAPublicKey
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
@ExperimentalUnsignedTypes
@FlowPreview
object TestUtils {
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
}
