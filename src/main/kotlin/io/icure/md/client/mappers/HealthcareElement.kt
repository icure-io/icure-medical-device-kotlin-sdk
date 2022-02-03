package io.icure.md.client.mappers

import io.icure.kraken.client.models.HealthElementDto
import io.icure.md.client.models.HealthcareElement

fun HealthElementDto.toHealthcareElement() = HealthcareElement(
    id = this.id,
    identifiers = this.identifiers.map { it.toIdentifier() },
    tags = this.tags.map { it.toCodingReference() },
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
)
