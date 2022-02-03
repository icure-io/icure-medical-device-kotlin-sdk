package io.icure.md.client.apis.e2e

import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.extendedapis.createPatient
import io.icure.kraken.client.models.decrypted.PatientDto
import io.icure.md.client.apis.DataSampleApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.impl.DataSampleApiImpl
import io.icure.md.client.models.CodingReference
import io.icure.md.client.models.Content
import io.icure.md.client.models.DataSample
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@DisplayName("Data Sample tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DataSampleApiImplTest {

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

    private val testedInstance: DataSampleApi = DataSampleApiImpl(medTechApi)

    @Test
    fun createOrModifyDataSamples_Success_Creation() {
        runBlocking {
            // Init
            val weight = weightDataSample()
            val height = heightDataSample()
            val currentUser = medTechApi.userApi().getCurrentUser()
            val existingPatient = medTechApi.patientApi()
                .createPatient(currentUser, patientDto(), patientCryptoConfig(medTechApi.localCrypto()))

            // When
            val createdDataSamples =
                testedInstance.createOrModifyDataSamplesFor(existingPatient.id, listOf(weight, height))

            // Then
            createdDataSamples.forEach { assert(it.id != null) }
            createdDataSamples.forEach { assert(it.batchId != null) }
            assertEquals(createdDataSamples[0].batchId, createdDataSamples[1].batchId)
        }
    }

    private fun heightDataSample() = DataSample(
        content = mapOf(
            "en" to Content(
                numberValue = 159.0
            )
        ),
        valueDate = 20220203111128,
        labels = listOf(CodingReference(id = "LOINC|8302-2|2", code = "8302-2", type = "LOINC", version = "2"))
    )

    private fun weightDataSample() = DataSample(
        content = mapOf(
            "en" to Content(
                numberValue = 53.5
            )
        ),
        valueDate = 20220203111034,
        labels = listOf(
            CodingReference(
                id = "LOINC|29463-7|2",
                code = "29463-7",
                type = "LOINC",
                version = "2"
            )
        )
    )

    private fun patientDto() = PatientDto(
        id = UUID.randomUUID().toString(),
        firstName = "Max",
        lastName = "LaMenace"
    )

    @Test
    fun createOrModifyDataSamples_Success_Update() {
        runBlocking {
            // Init

            // When

            // Then
        }
    }

    @Test
    fun createOrModifyDataSamples_Error_Update_For_Different_Batch_Ids() {
        // Init
        val patientId = UUID.randomUUID().toString()
        val weight = weightDataSample().copy(batchId = "batch-1")
        val height = heightDataSample().copy(batchId = "batch-2")

        // When
        assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                testedInstance.createOrModifyDataSamplesFor(
                    patientId,
                    listOf(weight, height)
                )
            }
        }
    }
}