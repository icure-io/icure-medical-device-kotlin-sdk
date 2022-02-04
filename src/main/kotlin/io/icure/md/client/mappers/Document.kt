package io.icure.md.client.mappers

import io.icure.kraken.client.models.decrypted.DocumentDto
import io.icure.md.client.models.Document
import java.util.*

fun DocumentDto.toDocument() = Document(
    id = this.id,
    otherUtis = this.otherUtis,
    rev = this.rev,
    created = this.created,
    modified = this.modified,
    author = this.author,
    responsible = this.responsible,
    medicalLocationId = this.medicalLocationId,
    deletionDate = this.deletionDate,
    objectStoreReference = this.objectStoreReference,
    mainUti = this.mainUti,
    name = this.name,
    version = this.version,
    externalUuid = this.externalUuid,
    propertySize = this.propertySize,
    hash = this.hash,
    attachmentId = this.attachmentId,
)

fun Document.toDocumentDto() = DocumentDto(
    id = this.id?.also {
        try {
            UUID.fromString(it)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid id, id must be a valid UUID")
        }
    } ?: UUID.randomUUID().toString(),
    otherUtis = this.otherUtis,
    rev = this.rev,
    created = this.created,
    modified = this.modified,
    author = this.author,
    responsible = this.responsible,
    medicalLocationId = this.medicalLocationId,
    deletionDate = this.deletionDate,
    objectStoreReference = this.objectStoreReference,
    mainUti = this.mainUti,
    name = this.name,
    version = this.version,
    externalUuid = this.externalUuid,
    propertySize = this.propertySize,
    hash = this.hash,
    attachmentId = this.attachmentId,
)
