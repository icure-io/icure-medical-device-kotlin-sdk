package io.icure.md.client.apis.e2e

import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.extendedapis.createPatient
import io.icure.kraken.client.models.decrypted.PatientDto
import io.icure.md.client.models.CodingReference
import io.icure.md.client.models.Content
import io.icure.md.client.models.DataSample
import io.icure.md.client.models.HealthcareElement
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.nio.ByteBuffer
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalTime
@ExperimentalUnsignedTypes
@DisplayName("Data Sample tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DataSampleApiImplTest {

    @Test
    fun createOrModifyDataSamples_HappyFlow_Creation() {
        runBlocking {
            // Init
            val credentials = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
            val medTechApi = credentials.api
            val weight = weightDataSample()
            val height = heightDataSample()
            val currentUser = medTechApi.baseUserApi.getCurrentUser()
            //val existingPatient = medTechApi.basePatientApi.createPatient(currentUser, patientDto(), patientCryptoConfig(medTechApi.localCrypto))

            // When
            val createdDataSamples = medTechApi.dataSampleApi().createOrModifyDataSamplesFor(
                currentUser.patientId ?: throw IllegalArgumentException("Test user should be a patient"),
                listOf(weight, height)
            )

            // Then
            createdDataSamples.forEach { assert(it.id != null) }
            createdDataSamples.forEach { assert(it.batchId != null) }
            assertEquals(createdDataSamples[0].batchId, createdDataSamples[1].batchId)
        }
    }

    @Test
    fun deleteDataSample_HappyFlow() {
        runBlocking {
            // Init
            val credentials = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
            val medTechApi = credentials.api
            val weight = weightDataSample()

            val currentUser = medTechApi.userApi().getLoggedUser()
            val patient = medTechApi.patientApi()
                .getPatient(currentUser.patientId ?: throw IllegalArgumentException("Test user should be a patient"))
            val createdDataSample = medTechApi.dataSampleApi().createOrModifyDataSampleFor(patient.id!!, weight)

            // When
            val deletedDataSampleId = medTechApi.dataSampleApi().deleteDataSample(createdDataSample.id!!)

            // Then
            val deletedDataSample = medTechApi.dataSampleApi().getDataSample(deletedDataSampleId)
            assert(deletedDataSample.endOfLife != null)
            assert(deletedDataSample.content.isEmpty())
        }
    }

    private fun heightDataSample() = DataSample(
        content = mapOf(
            "en" to Content(
                numberValue = 159.0
            )
        ),
        valueDate = 20220203111128,
        labels = listOf(
            CodingReference(id = "LOINC|8302-2|2", code = "8302-2", type = "LOINC", version = "2"),
            CodingReference(type = "IC-TEST", code = "TEST")
        )
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
            ),
            CodingReference(type = "IC-TEST", code = "TEST")
        )
    )

    private fun prescriptionDataSample() = DataSample(
        valueDate = 20220203111034,
        labels = listOf(
            CodingReference(
                id = "LOINC|57833-6|2",
                code = "57833-6",
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
    fun createOrModifyDataSamples_Error_Update_For_Different_Batch_Ids() {
        // Init
        val patientId = UUID.randomUUID().toString()
        val weight = weightDataSample().copy(batchId = "batch-1")
        val height = heightDataSample().copy(batchId = "batch-2")
        val credentials = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
        val medTechApi = credentials.api

        // When
        assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                medTechApi.dataSampleApi().createOrModifyDataSamplesFor(
                    patientId,
                    listOf(weight, height)
                )
            }
        }
    }

    @Test
    fun setDataSampleAttachment_HappyFlow() {
        runBlocking {
            // Init
            val credentials = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
            val medTechApi = credentials.api
            val weight = prescriptionDataSample()
            val currentUser = medTechApi.userApi().getLoggedUser()

            val existingPatient = medTechApi.patientApi()
                .getPatient(currentUser.patientId ?: throw IllegalArgumentException("Test user should be a patient"))
            val createdDataSample = medTechApi.dataSampleApi().createOrModifyDataSampleFor(existingPatient.id!!, weight)

            val documentToAdd =
                Files.readAllBytes(Paths.get("src/test/resources/io/icure/md/client/attachments/data_sample_attachment_note.xml"))

            // When
            val documentExternalUuid = UUID.randomUUID().toString()
            val createdDocument = medTechApi.dataSampleApi().setDataSampleAttachment(
                createdDataSample.id!!, flowOf(ByteBuffer.wrap(documentToAdd)),
                null, "1.0.0", documentExternalUuid, "en"
            )

            // Then
            assert(createdDocument.attachmentId != null)
            assertEquals("public.xml", createdDocument.mainUti)
            assertEquals(documentExternalUuid, createdDocument.externalUuid)

            val updatedDataSample = medTechApi.dataSampleApi().getDataSample(createdDataSample.id!!)
            assert(updatedDataSample.content["en"]?.documentId == createdDocument.id)
        }
    }

    @Test
    fun getDataSampleAttachment_HappyFlow() {
        runBlocking {
            val credentials = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
            val medTechApi = credentials.api
            // Init
            val dataSample = prescriptionDataSample()
            val currentUser = medTechApi.userApi().getLoggedUser()

            val existingPatient = medTechApi.patientApi()
                .getPatient(currentUser.patientId ?: throw IllegalArgumentException("Test user should be a patient"))
            val createdDataSample =
                medTechApi.dataSampleApi().createOrModifyDataSampleFor(existingPatient.id!!, dataSample)

            val attachmentToAdd =
                Files.readAllBytes(Paths.get("src/test/resources/io/icure/md/client/attachments/data_sample_attachment_note.xml"))
            val createdDocument = medTechApi.dataSampleApi().setDataSampleAttachment(
                createdDataSample.id!!, flowOf(ByteBuffer.wrap(attachmentToAdd)),
                null, "1.0.0", UUID.randomUUID().toString(), "en"
            )

            // When
            val attachmentDoc =
                medTechApi.dataSampleApi().getDataSampleAttachmentDocument(createdDataSample.id!!, createdDocument.id!!)
            val attachment = medTechApi.dataSampleApi().getDataSampleAttachmentContent(
                createdDataSample.id!!,
                createdDocument.id!!,
                createdDocument.attachmentId!!
            )
                .toCollection(mutableListOf())

            // Then
            assertEquals(createdDocument.id, attachmentDoc.id)
            assert(attachment.isNotEmpty())
        }
    }

    @Test
    fun deleteDataSampleAttachment_HappyFlow() {
        runBlocking {
            val credentials = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
            val medTechApi = credentials.api
            // Init
            val weight = prescriptionDataSample()
            val currentUser = medTechApi.baseUserApi.getCurrentUser()

            val existingPatient = medTechApi.basePatientApi
                .createPatient(currentUser, patientDto(), patientCryptoConfig(medTechApi.localCrypto))
            val createdDataSample = medTechApi.dataSampleApi().createOrModifyDataSampleFor(existingPatient.id, weight)

            val attachmentToAdd =
                Files.readAllBytes(Paths.get("src/test/resources/io/icure/md/client/attachments/data_sample_attachment_note.xml"))
            val createdDocument = medTechApi.dataSampleApi().setDataSampleAttachment(
                createdDataSample.id!!, flowOf(ByteBuffer.wrap(attachmentToAdd)),
                null, "1.0.0", UUID.randomUUID().toString(), "en"
            )

            // When
            val attachmentDocId =
                medTechApi.dataSampleApi().deleteAttachment(createdDataSample.id!!, createdDocument.id!!)

            // Then
            assertEquals(createdDocument.id, attachmentDocId)

            val updatedDataSample = medTechApi.dataSampleApi().getDataSample(createdDataSample.id!!)
            assert(updatedDataSample.content["en"]?.documentId == null)
        }
    }

    @Test
    @DisplayName("Create Data Sample linked to HealthElement - Success")
    fun createDataSampleLinkedToHealthElement() {
        runBlocking {
            val credentials = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
            val api = credentials.api

            val currentUser = api.userApi().getLoggedUser()
            val patient = api.patientApi()
                .getPatient(currentUser.patientId ?: throw IllegalArgumentException("Test user should be a patient"))
            val healthElement = api.healthcareElementApi()
                .createOrModifyHealthcareElement(patient.id!!, HealthcareElement(note = "Stay hungry. Stay foolish."))
            val dataSampleToCreate = weightDataSample().copy(
                healthElementsIds = setOf(healthElement.id!!)
            )
            val createdDataSample = api.dataSampleApi().createOrModifyDataSampleFor(patient.id!!, dataSampleToCreate)

            assert(createdDataSample.healthElementsIds!!.single() == healthElement.id)
        }
    }

    @Test
    @DisplayName("Create Data Sample and modify it to link it to HealthElement - Success")
    fun createDataSampleAndModifyItToLinkItToHealthElement() {
        runBlocking {
            val credentials = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
            val api = credentials.api

            val currentUser = api.userApi().getLoggedUser()
            val patient = api.patientApi()
                .getPatient(currentUser.patientId ?: throw IllegalArgumentException("Test user should be a patient"))
            val createdDataSample = api.dataSampleApi().createOrModifyDataSampleFor(patient.id!!, weightDataSample())

            val healthElement = api.healthcareElementApi()
                .createOrModifyHealthcareElement(patient.id!!, HealthcareElement(note = "Stay hungry. Stay foolish."))
            val updatedDataSample = api.dataSampleApi().createOrModifyDataSampleFor(
                patient.id!!,
                createdDataSample.copy(modified = null, healthElementsIds = setOfNotNull(healthElement.id))
            )

            assert(updatedDataSample.healthElementsIds!!.single() == healthElement.id)
        }
    }
}
