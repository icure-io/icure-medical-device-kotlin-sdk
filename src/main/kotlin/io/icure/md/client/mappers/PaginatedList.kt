package io.icure.md.client.mappers

import io.icure.kraken.client.models.PaginatedDocumentKeyIdPairObject
import io.icure.kraken.client.models.PaginatedListCodeDto
import io.icure.kraken.client.models.PaginatedListDeviceDto
import io.icure.kraken.client.models.PaginatedListHealthElementDto
import io.icure.kraken.client.models.PaginatedListHealthcarePartyDto
import io.icure.kraken.client.models.PaginatedListUserDto
import io.icure.kraken.client.models.decrypted.PaginatedListPatientDto
import io.icure.kraken.client.models.decrypted.PaginatedListServiceDto
import io.icure.md.client.models.PaginatedDocumentKeyAndIdPairObject
import io.icure.md.client.models.PaginatedListCoding
import io.icure.md.client.models.PaginatedListDataSample
import io.icure.md.client.models.PaginatedListHealthcareElement
import io.icure.md.client.models.PaginatedListHealthcareProfessional
import io.icure.md.client.models.PaginatedListMedicalDevice
import io.icure.md.client.models.PaginatedListPatient
import io.icure.md.client.models.PaginatedListUser

fun PaginatedListUserDto.toPaginatedListUser() = PaginatedListUser(
    pageSize = this.pageSize,
    totalSize = this.totalSize,
    rows = this.rows.map { it.toUser() },
    nextKeyPair = PaginatedDocumentKeyAndIdPairObject(this.nextKeyPair?.startKey, this.nextKeyPair?.startKeyDocId)
)

fun PaginatedListHealthElementDto.toPaginatedListHealthcareElement() = PaginatedListHealthcareElement(
    pageSize = this.pageSize,
    totalSize = this.totalSize,
    rows = this.rows.map { it.toHealthcareElement() },
    nextKeyPair = PaginatedDocumentKeyAndIdPairObject(this.nextKeyPair?.startKey, this.nextKeyPair?.startKeyDocId)
)

fun PaginatedListPatientDto.toPaginatedListPatient() = PaginatedListPatient(
    pageSize = this.pageSize,
    totalSize = this.totalSize,
    rows = this.rows.map { it.toPatient() },
    nextKeyPair = PaginatedDocumentKeyAndIdPairObject(this.nextKeyPair?.startKey, this.nextKeyPair?.startKeyDocId)
)

fun PaginatedListCodeDto.toPaginatedListCoding() = PaginatedListCoding(
    pageSize = this.pageSize,
    totalSize = this.totalSize,
    rows = this.rows.map { it.toCoding() },
    nextKeyPair = PaginatedDocumentKeyAndIdPairObject(this.nextKeyPair?.startKey, this.nextKeyPair?.startKeyDocId)
)

fun PaginatedListServiceDto.toPaginatedListDataSample() = PaginatedListDataSample(
    pageSize = this.pageSize,
    totalSize = this.totalSize,
    rows = this.rows.map { it.toDataSample() },
    nextKeyPair = PaginatedDocumentKeyAndIdPairObject(this.nextKeyPair?.startKey, this.nextKeyPair?.startKeyDocId)
)

fun PaginatedListHealthcarePartyDto.toPaginatedListHealthcareProfessional() = PaginatedListHealthcareProfessional(
    pageSize = this.pageSize,
    totalSize = this.totalSize,
    rows = this.rows.map { it.toHealthcareProfessional() },
    nextKeyPair = PaginatedDocumentKeyAndIdPairObject(this.nextKeyPair?.startKey, this.nextKeyPair?.startKeyDocId)
)

fun PaginatedListDeviceDto.toPaginatedListMedicalDevice(): PaginatedListMedicalDevice {
    return PaginatedListMedicalDevice(
        pageSize = this.pageSize,
        totalSize = this.totalSize,
        rows = this.rows.map { it.toMedicalDevice() },
        nextKeyPair = this.nextKeyPair?.toPaginatedDocumentKeyAndIdPairObject()
    )
}

fun PaginatedDocumentKeyIdPairObject.toPaginatedDocumentKeyAndIdPairObject(): PaginatedDocumentKeyAndIdPairObject {
    return PaginatedDocumentKeyAndIdPairObject(
        startKey = startKey,
        startKeyDocId = startKeyDocId
    )
}

