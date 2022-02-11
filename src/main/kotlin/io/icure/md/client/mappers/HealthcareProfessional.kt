package io.icure.md.client.mappers

import io.icure.kraken.client.models.HealthcarePartyDto
import io.icure.md.client.models.HealthcareProfessional
import io.icure.md.client.models.SystemMetaDataOwner
import io.icure.md.client.models.SystemMetaDataOwnerEncrypted
import java.util.*

fun HealthcarePartyDto.toHealthcareProfessional() = HealthcareProfessional(
    id = this.id,
    names = this.names.map { it.toPersonName() },
    addresses = this.addresses.map { it.toAddress() },
    languages = this.languages,
    specialityCodes = this.specialityCodes.map { it.toCodingReference() },
    properties = this.properties.map { it.toProperty() },
    rev = this.rev,
    deletionDate = this.deletionDate,
    name = this.name,
    lastName = this.lastName,
    firstName = this.firstName,
    gender = this.gender?.toGender(),
    civility = this.civility,
    speciality = this.speciality,
    parentId = this.parentId,
    picture = this.picture,
    notes = this.notes,
    systemMetaData = SystemMetaDataOwner(
        this.hcPartyKeys,
        this.privateKeyShamirPartitions,
    )
)

private fun HealthcarePartyDto.Gender.toGender() = HealthcareProfessional.Gender.valueOf(this.name)

fun HealthcareProfessional.toHealthcarePartyDto() = HealthcarePartyDto(
    id = this.id?.also {
        try {
            UUID.fromString(it)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid id, id must be a valid UUID")
        }
    } ?: UUID.randomUUID().toString(),
    names = this.names.map { it.toPersonNameDto() },
    addresses = this.addresses.map { it.toAddressDto() },
    languages = this.languages,
    specialityCodes = this.specialityCodes.map { it.toCodeStubDto() },
    properties = this.properties.map { it.toPropertyStubDto() },
    rev = this.rev,
    deletionDate = this.deletionDate,
    name = this.name,
    lastName = this.lastName,
    firstName = this.firstName,
    gender = this.gender?.toGender(),
    civility = this.civility,
    speciality = this.speciality,
    parentId = this.parentId,
    picture = this.picture,
    notes = this.notes,
    hcPartyKeys = this.systemMetaData?.hcPartyKeys ?: emptyMap(),
    privateKeyShamirPartitions = this.systemMetaData?.privateKeyShamirPartitions ?: emptyMap(),
)

private fun HealthcareProfessional.Gender.toGender() = HealthcarePartyDto.Gender.valueOf(this.name)
