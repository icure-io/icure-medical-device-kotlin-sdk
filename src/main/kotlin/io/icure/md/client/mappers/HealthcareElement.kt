package io.icure.md.client.mappers

import io.icure.kraken.client.models.decrypted.HealthElementDto
import io.icure.md.client.models.HealthcareElement
import io.icure.md.client.models.SystemMetaDataEncrypted
import java.util.*

fun HealthElementDto.toHealthcareElement() = HealthcareElement(
    id = this.id,
    identifiers = this.identifiers.map { it.toIdentifier() },
    labels = this.tags.map { it.toCodingReference() },
    codes = this.codes.map { it.toCodingReference() },
    rev = this.rev,
    created = this.created,
    modified = this.modified,
    author = this.author,
    responsible = this.responsible,
    medicalLocationId = this.medicalLocationId,
    endOfLife = this.endOfLife,
    deletionDate = this.deletionDate,
    healthElementId = this.healthElementId,
    valueDate = this.valueDate,
    openingDate = this.openingDate,
    closingDate = this.closingDate,
    description = this.descr,
    note = this.note,
    systemMetaData = SystemMetaDataEncrypted(
        this.secretForeignKeys,
        this.cryptedForeignKeys?.mapValues { (k, v) -> v.map { it.toDelegation() }.toSet() } ?: emptyMap(),
        this.delegations?.mapValues { (k, v) -> v.map { it.toDelegation() }.toSet() } ?: emptyMap(),
        this.encryptionKeys?.mapValues { (k, v) -> v.map { it.toDelegation() }.toSet() } ?: emptyMap()
    )
)

fun HealthcareElement.toHealthcareElementDto() = HealthElementDto(
    id = this.id?.also {
        try {
            UUID.fromString(it)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid id, id must be a valid UUID")
        }
    } ?: UUID.randomUUID().toString(),
    identifiers = this.identifiers.map { it.toIdentifierDto() },
    tags = this.labels.map { it.toCodeStubDto() },
    codes = this.codes.map { it.toCodeStubDto() },
    rev = this.rev,
    created = this.created,
    modified = this.modified,
    author = this.author,
    responsible = this.responsible,
    medicalLocationId = this.medicalLocationId,
    endOfLife = this.endOfLife,
    deletionDate = this.deletionDate,
    healthElementId = this.healthElementId,
    valueDate = this.valueDate,
    openingDate = this.openingDate,
    closingDate = this.closingDate,
    descr = this.description,
    note = this.note,
    relevant = true,
    status = 0,
    secretForeignKeys = this.systemMetaData?.secretForeignKeys ?: listOf(),
    cryptedForeignKeys = this.systemMetaData?.cryptedForeignKeys?.mapValues { (k, v) ->
        v.map { it.toDelegationDto() }.toSet()
    } ?: emptyMap(),
    delegations = this.systemMetaData?.delegations?.mapValues { (k, v) -> v.map { it.toDelegationDto() }.toSet() }
        ?: emptyMap(),
    encryptionKeys = this.systemMetaData?.encryptionKeys?.mapValues { (k, v) -> v.map { it.toDelegationDto() }.toSet() }
        ?: emptyMap(),
)
