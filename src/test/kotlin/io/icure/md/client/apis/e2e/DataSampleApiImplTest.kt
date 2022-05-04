package io.icure.md.client.apis.e2e

import io.icure.md.client.apis.DataSampleApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.impl.DataSampleApiImpl
import io.icure.md.client.models.CodingReference
import io.icure.md.client.models.DataSample
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalTime
@DisplayName("Data Sample tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DataSampleApiImplTest {

    private val iCurePath = "http://127.0.0.1:16043"
    private val authHeader = TestUtils.basicAuthFrom(".hkPatientCredentials")
    private val healthcareProfessionalId =
        "a37e0a71-07d2-4414-9b2b-2120ae9a16fc" ?: "782f1bcd-9f3f-408a-af1b-cd9f3f908a98"
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

//    @Test
//    fun createOrModifyDataSamples_HappyFlow_Creation() {
//        runBlocking {
//            // Init
//            val weight = weightDataSample()
//            val height = heightDataSample()
//            val currentUser = medTechApi.baseUserApi.getCurrentUser()
//            //val existingPatient = medTechApi.basePatientApi.createPatient(currentUser, patientDto(), patientCryptoConfig(medTechApi.localCrypto))
//
//            // When
//            val createdDataSamples = testedInstance.createOrModifyDataSamplesFor(
//                "a37e0a71-07d2-4414-9b2b-2120ae9a16fc",
//                listOf(weight, height)
//            )
//
//            // Then
//            createdDataSamples.forEach { assert(it.id != null) }
//            createdDataSamples.forEach { assert(it.batchId != null) }
//            assertEquals(createdDataSamples[0].batchId, createdDataSamples[1].batchId)
//        }
//    }
//
//    @Test
//    fun deleteDataSample_HappyFlow() {
//        runBlocking {
//            // Init
//            val weight = weightDataSample()
//            val currentUser = medTechApi.baseUserApi.getCurrentUser()
//
//            val existingPatient = medTechApi.basePatientApi
//                .createPatient(currentUser, patientDto(), patientCryptoConfig(medTechApi.localCrypto))
//            val createdDataSample = testedInstance.createOrModifyDataSampleFor(existingPatient.id, weight)
//
//            // When
//            val deletedDataSampleId = testedInstance.deleteDataSample(createdDataSample.id!!)
//
//            // Then
//            val deletedDataSample = testedInstance.getDataSample(deletedDataSampleId)
//            assert(deletedDataSample.endOfLife != null)
//            assert(deletedDataSample.content.isEmpty())
//        }
//    }
//
//    private fun heightDataSample() = DataSample(
//        content = mapOf(
//            "en" to Content(
//                numberValue = 159.0
//            )
//        ),
//        valueDate = 20220203111128,
//        labels = listOf(CodingReference(id = "LOINC|8302-2|2", code = "8302-2", type = "LOINC", version = "2"))
//    )
//
//    private fun weightDataSample() = DataSample(
//        content = mapOf(
//            "en" to Content(
//                numberValue = 53.5
//            )
//        ),
//        valueDate = 20220203111034,
//        labels = listOf(
//            CodingReference(
//                id = "LOINC|29463-7|2",
//                code = "29463-7",
//                type = "LOINC",
//                version = "2"
//            )
//        )
//    )
//
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
//
//    private fun patientDto() = PatientDto(
//        id = UUID.randomUUID().toString(),
//        firstName = "Max",
//        lastName = "LaMenace"
//    )
//
//    @Test
//    fun createOrModifyDataSamples_Error_Update_For_Different_Batch_Ids() {
//        // Init
//        val patientId = UUID.randomUUID().toString()
//        val weight = weightDataSample().copy(batchId = "batch-1")
//        val height = heightDataSample().copy(batchId = "batch-2")
//
//        // When
//        assertThrows(IllegalArgumentException::class.java) {
//            runBlocking {
//                testedInstance.createOrModifyDataSamplesFor(
//                    patientId,
//                    listOf(weight, height)
//                )
//            }
//        }
//    }
//
//    @Test
//    fun setDataSampleAttachment_HappyFlow() {
//        runBlocking {
//            // Init
//            val weight = prescriptionDataSample()
//            val currentUser = medTechApi.baseUserApi.getCurrentUser()
//
//            val existingPatient = medTechApi.basePatientApi
//                .createPatient(currentUser, patientDto(), patientCryptoConfig(medTechApi.localCrypto))
//            val createdDataSample = testedInstance.createOrModifyDataSampleFor(existingPatient.id, weight)
//
//            val documentToAdd =
//                Files.readAllBytes(Paths.get("src/test/resources/io/icure/md/client/attachments/data_sample_attachment_note.xml"))
//
//            // When
//            val documentExternalUuid = UUID.randomUUID().toString()
//            val createdDocument = testedInstance.setDataSampleAttachment(
//                createdDataSample.id!!, flowOf(ByteBuffer.wrap(documentToAdd)),
//                null, "1.0.0", documentExternalUuid, "en"
//            )
//
//            // Then
//            assert(createdDocument.attachmentId != null)
//            assertEquals("public.xml", createdDocument.mainUti)
//            assertEquals(documentExternalUuid, createdDocument.externalUuid)
//
//            val updatedDataSample = testedInstance.getDataSample(createdDataSample.id!!)
//            assert(updatedDataSample.content["en"]?.documentId == createdDocument.id)
//        }
//    }
//
//    @Test
//    fun getDataSampleAttachment_HappyFlow() {
//        runBlocking {
//            // Init
//            val weight = prescriptionDataSample()
//            val currentUser = medTechApi.baseUserApi.getCurrentUser()
//
//            val existingPatient = medTechApi.basePatientApi
//                .createPatient(currentUser, patientDto(), patientCryptoConfig(medTechApi.localCrypto))
//            val createdDataSample = testedInstance.createOrModifyDataSampleFor(existingPatient.id, weight)
//
//            val attachmentToAdd =
//                Files.readAllBytes(Paths.get("src/test/resources/io/icure/md/client/attachments/data_sample_attachment_note.xml"))
//            val createdDocument = testedInstance.setDataSampleAttachment(
//                createdDataSample.id!!, flowOf(ByteBuffer.wrap(attachmentToAdd)),
//                null, "1.0.0", UUID.randomUUID().toString(), "en"
//            )
//
//            // When
//            val attachmentDoc =
//                testedInstance.getDataSampleAttachmentDocument(createdDataSample.id!!, createdDocument.id!!)
//            val attachment = testedInstance.getDataSampleAttachmentContent(
//                createdDataSample.id!!,
//                createdDocument.id!!,
//                createdDocument.attachmentId!!
//            )
//                .toCollection(mutableListOf())
//
//            // Then
//            assertEquals(createdDocument.id, attachmentDoc.id)
//            assert(attachment.isNotEmpty())
//        }
//    }
//
//    @Test
//    fun deleteDataSampleAttachment_HappyFlow() {
//        runBlocking {
//            // Init
//            val weight = prescriptionDataSample()
//            val currentUser = medTechApi.baseUserApi.getCurrentUser()
//
//            val existingPatient = medTechApi.basePatientApi
//                .createPatient(currentUser, patientDto(), patientCryptoConfig(medTechApi.localCrypto))
//            val createdDataSample = testedInstance.createOrModifyDataSampleFor(existingPatient.id, weight)
//
//            val attachmentToAdd =
//                Files.readAllBytes(Paths.get("src/test/resources/io/icure/md/client/attachments/data_sample_attachment_note.xml"))
//            val createdDocument = testedInstance.setDataSampleAttachment(
//                createdDataSample.id!!, flowOf(ByteBuffer.wrap(attachmentToAdd)),
//                null, "1.0.0", UUID.randomUUID().toString(), "en"
//            )
//
//            // When
//            val attachmentDocId = testedInstance.deleteAttachment(createdDataSample.id!!, createdDocument.id!!)
//
//            // Then
//            assertEquals(createdDocument.id, attachmentDocId)
//
//            val updatedDataSample = testedInstance.getDataSample(createdDataSample.id!!)
//            assert(updatedDataSample.content["en"]?.documentId == null)
//        }
//    }

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

            assert(sharedDS == gotDataSampleFromHcp)
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

            assert(sharedDS == gotDataSampleFromPat)
        }
    }
}
