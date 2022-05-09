package io.icure.md.client.apis.e2e

import io.icure.kraken.client.crypto.toPrivateKey
import io.icure.md.client.apis.HealthcareProfessionalApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.impl.HealthcareProfessionalApiImpl
import io.icure.md.client.models.HealthcareProfessional
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.Instant
import kotlin.time.ExperimentalTime

@ExperimentalUnsignedTypes
@FlowPreview
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalTime
@DisplayName("HealthcareProfessional tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HealthcareProfessionalApiImplTest {

    private val iCurePath = System.getenv("TEST_ICURE_URL") ?: "https://kraken.icure.dev"
    private val authHeader = TestUtils.basicAuthFrom()
    private val healthcareProfessionalId = System.getenv("TEST_HCP_ID")
    private val healthcareProfessionalPrivateKey = System.getenv("TEST_HCP_PRIV_KEY").toPrivateKey()
    private val healthcareProfessionalPublicKey =
        runBlocking { TestUtils.healthcareProfessionalPublicKey(iCurePath, authHeader, healthcareProfessionalId) }

    private val medTechApi = MedTechApi.Builder()
        .iCureUrlPath(iCurePath)
        .authorization(authHeader)
        .addKeyPair(healthcareProfessionalId, healthcareProfessionalPublicKey, healthcareProfessionalPrivateKey)
        .build()

    private val testedInstance: HealthcareProfessionalApi = HealthcareProfessionalApiImpl(medTechApi)

    @Test
    fun createOrModifyHealthcareProfessional_Success_Creation() {
        runBlocking {
            // When
            val createdHealthcareProfessional =
                testedInstance.createOrModifyHealthcareProfessional(HealthcareProfessional(name = "Jean Vallejean${Instant.now().toEpochMilli()}"))

            // Then
            assert(createdHealthcareProfessional.id != null)
        }
    }

}
