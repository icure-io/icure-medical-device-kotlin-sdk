package io.icure.md.client.apis.impl

import io.icure.kraken.client.crypto.LocalCrypto
import io.icure.kraken.client.crypto.contactCryptoConfig
import io.icure.kraken.client.crypto.documentCryptoConfig
import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.extendedapis.createContact
import io.icure.kraken.client.extendedapis.createDocument
import io.icure.kraken.client.extendedapis.deleteServices
import io.icure.kraken.client.extendedapis.filterContactsBy
import io.icure.kraken.client.extendedapis.getContact
import io.icure.kraken.client.extendedapis.getDocument
import io.icure.kraken.client.extendedapis.getPatient
import io.icure.kraken.client.extendedapis.listServices
import io.icure.kraken.client.extendedapis.modifyDocument
import io.icure.kraken.client.extendedapis.setDocumentAttachment
import io.icure.kraken.client.models.ListOfIdsDto
import io.icure.kraken.client.models.UserDto
import io.icure.kraken.client.models.decrypted.ContactDto
import io.icure.kraken.client.models.decrypted.DocumentDto
import io.icure.kraken.client.models.decrypted.PatientDto
import io.icure.kraken.client.models.decrypted.ServiceDto
import io.icure.kraken.client.models.filter.chain.FilterChain
import io.icure.kraken.client.models.filter.contact.ContactByServiceIdsFilter
import io.icure.md.client.apis.DataSampleApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.mappers.findDataOwnerId
import io.icure.md.client.mappers.toDataSample
import io.icure.md.client.mappers.toDocument
import io.icure.md.client.mappers.toServiceDto
import io.icure.md.client.models.Content
import io.icure.md.client.models.DataSample
import io.icure.md.client.models.Document
import io.icure.md.client.models.PaginatedListDataSample
import io.icure.md.client.toHex
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.taktik.commons.uti.UTI
import org.taktik.commons.uti.impl.SimpleUTIDetector
import java.io.ByteArrayInputStream
import java.nio.ByteBuffer
import java.security.MessageDigest
import java.util.*

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class DataSampleApiImpl(private val medTechApi: MedTechApi) : DataSampleApi {
    private val utiDetector = SimpleUTIDetector()

    override suspend fun createOrModifyDataSampleFor(patientId: String, dataSample: DataSample): DataSample {
        return createOrModifyDataSamplesFor(patientId, listOf(dataSample)).first()
    }

    override suspend fun createOrModifyDataSamplesFor(
        patientId: String,
        dataSample: List<DataSample>
    ): List<DataSample> {
        if (dataSample.distinctBy { ds -> ds.batchId }.count() > 1) {
            throw IllegalArgumentException("Only data samples of a same batch can be processed together")
        }

        if (countHierarchyOfDataSamples(0, 0, dataSample) > 1000) { // Arbitrary : 1 service = 1K
            throw IllegalArgumentException("Can't process more than 1000 data samples in the same batch")
        }

        val localCrypto = medTechApi.localCrypto()
        val currentUser = medTechApi.userApi().getCurrentUser()
        val existingContact = getContactOfDataSample(localCrypto, currentUser, dataSample.first())

        val contactPatientId = existingContact?.let {
            getPatientIdOfContact(localCrypto, currentUser, it)
        }

        if (contactPatientId != null && contactPatientId != patientId) {
            throw IllegalArgumentException("Can't update the patient of a batch of data samples. Delete those samples and create new ones")
        }

        val existingPatient =
            medTechApi.patientApi().getPatient(currentUser, patientId, patientCryptoConfig(localCrypto))
        val contactToCreate = createContactDtoBasedOn(dataSample, existingContact)

        return medTechApi.contactApi()
            .createContact(currentUser, existingPatient, contactToCreate, contactCryptoConfig(localCrypto, currentUser))
            .let { createdContact ->
                createdContact.services.map { it.toDataSample(batchId = contactToCreate.id) }
            }
    }

    private suspend fun getContactOfDataSample(
        localCrypto: LocalCrypto,
        currentUser: UserDto,
        dataSample: DataSample
    ): ContactDto? {
        return dataSample.batchId?.let { contactId -> getContactFromICure(localCrypto, currentUser, contactId) }
    }

    private suspend fun getContactFromICure(
        localCrypto: LocalCrypto,
        currentUser: UserDto,
        contactId: String
    ): ContactDto {
        return medTechApi.contactApi().getContact(currentUser, contactId, contactCryptoConfig(localCrypto, currentUser))
    }

    private suspend fun getPatientIdOfContact(
        localCrypto: LocalCrypto,
        currentUser: UserDto,
        contact: ContactDto
    ) = localCrypto.decryptEncryptionKeys(currentUser.findDataOwnerId(), contact.cryptedForeignKeys)
        .firstOrNull()

    private suspend fun getPatientOfContact(
        localCrypto: LocalCrypto,
        currentUser: UserDto,
        contact: ContactDto
    ): PatientDto? {
        return getPatientIdOfContact(localCrypto, currentUser, contact)
            ?.let { medTechApi.patientApi().getPatient(currentUser, it, patientCryptoConfig(localCrypto)) }
    }

    private fun countHierarchyOfDataSamples(
        currentCount: Int,
        dataSampleIndex: Int,
        dataSamples: List<DataSample>
    ): Int {
        if (dataSampleIndex >= dataSamples.size) {
            return currentCount
        }

        val currentDataSample = dataSamples[dataSampleIndex]
        val dataSampleCount = currentDataSample.content.values
            .filterNot { it.compoundValue.isNullOrEmpty() }
            .sumOf { countHierarchyOfDataSamples(0, 0, it.compoundValue!!) }

        return countHierarchyOfDataSamples(currentCount + dataSampleCount, dataSampleIndex + 1, dataSamples)
    }

    private fun createContactDtoBasedOn(
        dataSamples: List<DataSample>,
        existingContact: ContactDto? = null
    ): ContactDto {
        val servicesToCreate = dataSamples.map { it.toServiceDto() }
        return createContactDtoUsing(servicesToCreate, existingContact)
    }

    private fun createContactDtoUsing(
        servicesToCreate: List<ServiceDto>,
        existingContact: ContactDto? = null
    ): ContactDto {
        val baseContact =
            existingContact?.copy(id = UUID.randomUUID().toString(), rev = null, modified = System.currentTimeMillis())
                ?: ContactDto(id = UUID.randomUUID().toString())

        return baseContact.copy(
            services = servicesToCreate,
            openingDate = servicesToCreate.mapNotNull { it.openingDate ?: it.valueDate }
                .minOfOrNull { it },
            closingDate = servicesToCreate.mapNotNull { it.closingDate ?: it.valueDate }
                .maxOfOrNull { it }
        )
    }

    override suspend fun deleteAttachment(dataSampleId: String, documentId: String): String {
        val localCrypto = medTechApi.localCrypto()
        val currentUser = medTechApi.userApi().getCurrentUser()

        val existingService = getServiceFromICure(dataSampleId)
        val existingContact = existingService?.contactId
            ?.let { getContactFromICure(localCrypto, currentUser, it) }
            ?: throw RuntimeException("Could not find batch information of the data sample $dataSampleId")

        val patientOfContact = getPatientOfContact(localCrypto, currentUser, existingContact)
            ?: throw IllegalArgumentException("Can not set an attachment to a data sample not linked to a patient")

        val contentToDelete = existingService.content.entries.find { (_, content) -> content.documentId == documentId }
            ?.key
            ?: throw IllegalArgumentException("Id $documentId does not reference any document in the data sample $dataSampleId")

        val contactToCreate = createContactDtoUsing(
            existingContact.services
                .filterNot { it.id != existingService.id } + listOf(existingService.copy(content = existingService.content.filterKeys { it != contentToDelete })),
            existingContact
        )

        medTechApi.contactApi()
            .createContact(
                currentUser,
                patientOfContact,
                contactToCreate,
                contactCryptoConfig(localCrypto, currentUser)
            )

        return documentId
    }

    override suspend fun deleteDataSample(dataSampleId: String): String {
        return deleteDataSamples(listOf(dataSampleId)).firstOrNull()
            ?: throw RuntimeException("Couldn't delete data sample $dataSampleId")
    }

    override suspend fun deleteDataSamples(dataSampleIds: List<String>): List<String> {
        val localCrypto = medTechApi.localCrypto()
        val contactApi = medTechApi.contactApi()
        val currentUser = medTechApi.userApi().getCurrentUser()

        val existingContacts = contactApi
            .filterContactsBy(
                currentUser, FilterChain(ContactByServiceIdsFilter(ids = dataSampleIds)), null, null,
                dataSampleIds.size, null, null, null, contactCryptoConfig(localCrypto, currentUser)
            )
            .rows

        if (existingContacts.size > 1) {
            throw IllegalArgumentException("Only data samples of a same batch can be processed together")
        }

        val existingContact = existingContacts.first()
        val contactPatient = getPatientOfContact(localCrypto, currentUser, existingContact)
            ?: throw RuntimeException("Couldn't find patient related to batch of data samples ${existingContact.id}")

        val servicesToDelete = existingContact.services.filter { it.id in dataSampleIds }

        return medTechApi.contactApi().deleteServices(
            currentUser,
            contactPatient,
            servicesToDelete,
            contactCryptoConfig(localCrypto, currentUser)
        )
            .services
            .filter { it.id in dataSampleIds }
            .filter { it.endOfLife != null }
            .map { it.id }
    }

    override suspend fun filterDataSample(filter: Filter<DataSample>): PaginatedListDataSample {
        TODO("Not yet implemented")
    }

    override suspend fun getDataSample(dataSampleId: String): DataSample {
        return getServiceFromICure(dataSampleId)
            ?.toDataSample()
            ?: throw IllegalArgumentException("Id $dataSampleId does not correspond to any existing data sample")
    }

    private suspend fun getServiceFromICure(dataSampleId: String): ServiceDto? {
        val localCrypto = medTechApi.localCrypto()
        val currentUser = medTechApi.userApi().getCurrentUser()

        return medTechApi.contactApi().listServices(
            currentUser,
            ListOfIdsDto(listOf(dataSampleId)),
            contactCryptoConfig(localCrypto, currentUser).crypto
        )
            .firstOrNull()
    }

    override suspend fun getDataSampleAttachmentDocument(dataSampleId: String, documentId: String): Document {
        val localCrypto = medTechApi.localCrypto()
        val currentUser = medTechApi.userApi().getCurrentUser()

        return getDataSampleAttachmentDocumentFromICure(localCrypto, currentUser, dataSampleId, documentId)
            .toDocument()
    }

    override suspend fun getDataSampleAttachmentContent(
        dataSampleId: String,
        documentId: String,
        attachmentId: String
    ): Flow<ByteBuffer> {
        val localCrypto = medTechApi.localCrypto()
        val currentUser = medTechApi.userApi().getCurrentUser()

        val documentOfAttachment =
            getDataSampleAttachmentDocumentFromICure(localCrypto, currentUser, dataSampleId, documentId)

        return medTechApi.documentApi().getDocumentAttachment(
            documentId,
            attachmentId,
            getDocumentEncryptionKeys(localCrypto, currentUser, documentOfAttachment).joinToString(","),
            null
        )
    }

    private suspend fun getDataSampleAttachmentDocumentFromICure(
        localCrypto: LocalCrypto,
        currentUser: UserDto,
        dataSampleId: String,
        documentId: String
    ): DocumentDto {
        val existingDataSample = getDataSample(dataSampleId)

        if (existingDataSample.content.entries.find { (_, content) -> content.documentId == documentId } == null) {
            throw IllegalArgumentException("Id $documentId does not reference any document in the data sample $dataSampleId")
        }

        return medTechApi.documentApi().getDocument(currentUser, documentId, documentCryptoConfig(localCrypto))
    }

    override suspend fun matchDataSample(filter: Filter<DataSample>): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun setDataSampleAttachment(
        dataSampleId: String,
        body: Flow<ByteBuffer>,
        documentName: String?,
        documentVersion: String?,
        documentExternalUuid: String?,
        documentLanguage: String?
    ): Document {
        val localCrypto = medTechApi.localCrypto()
        val currentUser = medTechApi.userApi().getCurrentUser()

        val existingDataSample = getDataSample(dataSampleId)
        val existingContact = getContactOfDataSample(localCrypto, currentUser, existingDataSample)
            ?: throw RuntimeException("Could not find batch information of the data sample $dataSampleId")
        val patientOfContact = getPatientIdOfContact(localCrypto, currentUser, existingContact)
            ?: throw IllegalArgumentException("Can not set an attachment to a data sample not linked to a patient")

        val documentToCreate = DocumentDto(
            id = UUID.randomUUID().toString(),
            name = documentName,
            version = documentVersion,
            externalUuid = documentExternalUuid,
        )

        val documentConfig = documentCryptoConfig(localCrypto)
        val md = MessageDigest.getInstance("SHA-256")
        var uti: UTI? = null

        val createdDocument = medTechApi.documentApi().createDocument(currentUser, documentToCreate, documentConfig)
            .let { createdDoc ->
                medTechApi.documentApi().setDocumentAttachment(
                    user = currentUser,
                    documentId = createdDoc.id,
                    requestBody = body.map {
                        if (uti == null) {
                            val byteArray = ByteArray(it.remaining().coerceAtMost(256))
                            it.slice().get(byteArray, 0, byteArray.size)
                            uti = utiDetector.detectUTI(ByteArrayInputStream(byteArray), null, null)
                        }
                        md.update(it.slice())
                        it
                    },
                    enckeys = getDocumentEncryptionKeys(localCrypto, currentUser, createdDoc).firstOrNull(),
                    config = documentConfig
                )
            }

        // Updating data sample with docId
        val contentIso = documentLanguage ?: medTechApi.defaultLanguage()
        createOrModifyDataSampleFor(
            patientOfContact,
            existingDataSample.copy(content = mapOf(contentIso to Content(documentId = createdDocument.id)))
        )

        // Add the hash and UTI of the document
        val finalDoc = medTechApi.documentApi()
            .modifyDocument(
                currentUser,
                createdDocument.copy(hash = md.digest().toHex(), mainUti = uti.toString()),
                documentConfig
            )

        return finalDoc.toDocument()
    }

    private suspend fun getDocumentEncryptionKeys(
        localCrypto: LocalCrypto,
        currentUser: UserDto,
        document: DocumentDto
    ) = localCrypto.decryptEncryptionKeys(currentUser.findDataOwnerId(), document.encryptionKeys)
}
