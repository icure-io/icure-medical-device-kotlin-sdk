package io.icure.md.client.apis.e2e

import io.icure.kraken.client.models.decrypted.PatientDto
import io.icure.md.client.filter.byHealthcareElementIds
import io.icure.md.client.filter.filter
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
            val credentials = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
            val medTechApi = credentials.api
            val weight = weightDataSample()
            val height = heightDataSample()
            val currentUser = medTechApi.baseUserApi.getCurrentUser()

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
            val credentials = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
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
            ),
            CodingReference(
                type = "IC_TEST",
                code = "TEST"
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
        val credentials = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
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
            val credentials = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
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
            val credentials = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
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
            val credentials = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
            val medTechApi = credentials.api
            // Init
            val weight = prescriptionDataSample()
            val currentUser = medTechApi.userApi().getLoggedUser()

            val existingPatient = medTechApi.patientApi()
                .getPatient(currentUser.patientId ?: throw IllegalArgumentException("Test user should be a patient"))
            val createdDataSample = medTechApi.dataSampleApi().createOrModifyDataSampleFor(existingPatient.id!!, weight)

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
            val credentials = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
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

            assert(healthElement.id!! in createdDataSample.healthElementsIds!!)

            val contactOfDataSample = api.baseContactApi.getContact(createdDataSample.batchId!!)
            assert(createdDataSample.healthElementsIds!!.all { it in contactOfDataSample.subContacts.map { subContactDto -> subContactDto.healthElementId } })
        }
    }

    @Test
    @DisplayName("Create Data Sample and modify it to link it to HealthElement - Success")
    fun createDataSampleAndModifyItToLinkItToHealthElement() {
        runBlocking {
            val credentials = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
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

            assert(healthElement.id!! in updatedDataSample.healthElementsIds!!)

            val contactOfDataSample = api.baseContactApi.getContact(createdDataSample.batchId!!)
            assert(createdDataSample.healthElementsIds!!.all { it in contactOfDataSample.subContacts.map { subContactDto -> subContactDto.healthElementId } })
        }
    }

    @Test
    @DisplayName("Share delegation of a DataSample from patient to HCP")
    fun shareDelegationOfADataSampleFromPatientToHcp() {
        runBlocking {
            val patCred = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
            val hcpCred = TestUtils.UserCredentials.fromFile("hcp_2c5f952e-512b-4fd3-bc6d-0f66c282c159.json")

            val currentPatUser = patCred.api.userApi().getLoggedUser()
            val currentHcpUser = hcpCred.api.userApi().getLoggedUser()

            val patientFromPat = patCred.api.patientApi()
                .getPatient(currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient"))
            val createdDataSampleFromPat =
                patCred.api.dataSampleApi().createOrModifyDataSampleFor(patientFromPat.id!!, prescriptionDataSample())
            val sharedDS = patCred.api.dataSampleApi().giveAccessTo(
                createdDataSampleFromPat,
                currentHcpUser.healthcarePartyId ?: throw IllegalArgumentException("User must be a HCP")
            )
            val gotDataSampleFromHcp = hcpCred.api.dataSampleApi().getDataSample(sharedDS.id!!)

            assertEquals(sharedDS, gotDataSampleFromHcp)
        }
    }

    @Test
    @DisplayName("Share delegation of a DataSample from HCP to Patient")
    fun shareDelegationOfADataSampleFromHcpToPatient() {
        runBlocking {
            val patCred = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
            val hcpCred = TestUtils.UserCredentials.fromFile("hcp_2c5f952e-512b-4fd3-bc6d-0f66c282c159.json")

            val currentPatUser = patCred.api.userApi().getLoggedUser()
            val currentHcpUser = hcpCred.api.userApi().getLoggedUser()

            val patientFromPat = patCred.api.patientApi()
                .getPatient(currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient"))
            val delegatedPatient = patCred.api.patientApi().giveAccessTo(
                patientFromPat,
                currentHcpUser.healthcarePartyId ?: throw IllegalArgumentException("User must be a HCP")
            )

            val createdDataSampleFromHcp =
                hcpCred.api.dataSampleApi().createOrModifyDataSampleFor(patientFromPat.id!!, prescriptionDataSample())
            val sharedDS = hcpCred.api.dataSampleApi().giveAccessTo(
                createdDataSampleFromHcp,
                currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient")
            )
            val gotDataSampleFromPat = patCred.api.dataSampleApi().getDataSample(sharedDS.id!!)

            assertEquals(sharedDS, gotDataSampleFromPat)
        }
    }

    @Test
    @DisplayName("Filter data samples by HealthcareElementIds - Success")
    fun filterDataSamplesByHealthcareElementIds_HappyFlow() {
        runBlocking {
            // Given
            val patCred = TestUtils.UserCredentials.fromFile("pat_efa2933a-6bcf-4ab9-b9e7-60604fcb956f.json")

            val currentUser = patCred.api.userApi().getLoggedUser()
            val patientFromPat = patCred.api.patientApi()
                .getPatient(currentUser.patientId ?: throw IllegalArgumentException("User must be a Patient"))

            val createdHealthcareElement = patCred.api.healthcareElementApi()
                .createOrModifyHealthcareElement(
                    patientFromPat.id!!,
                    HealthcareElement(note = "Stay hungry. Stay foolish.")
                )
            val dataSampleToCreate = weightDataSample().copy(
                healthElementsIds = setOf(createdHealthcareElement.id!!)
            )

            val createdDataSample =
                patCred.api.dataSampleApi().createOrModifyDataSampleFor(patientFromPat.id!!, dataSampleToCreate)

            val filter = filter<DataSample> {
                forDataOwner(currentUser.patientId)
                byHealthcareElementIds(createdHealthcareElement.id!!)
            }.build()

            val filteredDataSamples = patCred.api.dataSampleApi().filterDataSamples(filter, null, null)
            assert(filteredDataSamples.rows.size == 1)
            assert(filteredDataSamples.rows.first().id == createdDataSample.id)
            assert(createdHealthcareElement.id in filteredDataSamples.rows.first().healthElementsIds!!)
        }
    }
}
