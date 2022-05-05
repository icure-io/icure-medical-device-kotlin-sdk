package io.icure.md.client.mappers

import io.icure.kraken.client.models.HealthcarePartyDto
import io.icure.md.client.models.HealthcareProfessional
import io.icure.md.client.models.SystemMetaDataOwner
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
        hcPartyKeys = this.hcPartyKeys,
        privateKeyShamirPartitions = this.privateKeyShamirPartitions,
        aesExchangeKeys = this.aesExchangeKeys,
        transferKeys = this.transferKeys,
        lostHcPartyKeys = this.lostHcPartyKeys
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
    aesExchangeKeys = this.systemMetaData?.aesExchangeKeys ?: emptyMap(),
    transferKeys = this.systemMetaData?.transferKeys ?: emptyMap(),
    lostHcPartyKeys = this.systemMetaData?.lostHcPartyKeys ?: emptyList()
)

private fun HealthcareProfessional.Gender.toGender() = HealthcarePartyDto.Gender.valueOf(this.name)
