package io.icure.md.client.mappers

import io.icure.kraken.client.models.DocumentDto
import io.icure.md.client.models.Document

fun DocumentDto.toDocument() = Document(
    id = this.id,
    labels = this.tags.map { it.toCodingReference() },
    codes = this.codes.map { it.toCodingReference() },
    otherUtis = this.otherUtis,
    rev = this.rev,
    created = this.created,
    modified = this.modified,
    author = this.author,
    responsible = this.responsible,
    medicalLocationId = this.medicalLocationId,
    endOfLife = this.endOfLife,
    deletionDate = this.deletionDate,
    objectStoreReference = this.objectStoreReference,
    externalUri = this.externalUri,
    mainUti = this.mainUti,
    name = this.name,
    version = this.version,
    externalUuid = this.externalUuid,
    propertySize = this.propertySize,
    hash = this.hash,
    attachmentId = this.attachmentId,
)

fun Document.toDocumentDto() = DocumentDto(
    id = this.id,
    tags = this.labels.map { it.toCodeStubDto() },
    codes = this.codes.map { it.toCodeStubDto() },
    otherUtis = this.otherUtis,
    rev = this.rev,
    created = this.created,
    modified = this.modified,
    author = this.author,
    responsible = this.responsible,
    medicalLocationId = this.medicalLocationId,
    endOfLife = this.endOfLife,
    deletionDate = this.deletionDate,
    objectStoreReference = this.objectStoreReference,
    externalUri = this.externalUri,
    mainUti = this.mainUti,
    name = this.name,
    version = this.version,
    externalUuid = this.externalUuid,
    propertySize = this.propertySize,
    hash = this.hash,
    attachmentId = this.attachmentId,
)
