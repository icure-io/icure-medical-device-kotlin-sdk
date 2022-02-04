package io.icure.md.client.apis.e2e

import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.extendedapis.createPatient
import io.icure.kraken.client.models.decrypted.PatientDto
import io.icure.md.client.apis.HealthcareProfessionalApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.impl.HealthcareProfessionalApiImpl
import io.icure.md.client.models.CodingReference
import io.icure.md.client.models.Content
import io.icure.md.client.models.DataSample
import io.icure.md.client.models.HealthcareProfessional
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.Instant
import java.util.*

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@DisplayName("Data Sample tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HealthcareProfessionalApiImplTest {

    private val iCurePath = "https://kraken.icure.dev"
    private val authHeader = TestUtils.basicAuthFrom(".credentials")
    private val healthcareProfessionalId = "782f1bcd-9f3f-408a-af1b-cd9f3f908a98"
    private val healthcareProfessionalPrivateKey =
        TestUtils.healthcareProfessionalPrivateKey(healthcareProfessionalId, this::class.java)
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
