package io.icure.md.client.mappers

import io.icure.kraken.client.models.HealthcarePartyDto
import io.icure.md.client.models.HealthcareProfessional

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
            )

private fun HealthcarePartyDto.Gender.toGender() = HealthcareProfessional.Gender.valueOf(this.name)

fun HealthcareProfessional.toHealthcarePartyDto() = HealthcarePartyDto(
        id = this.id,
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
)

private fun HealthcareProfessional.Gender.toGender() = HealthcarePartyDto.Gender.valueOf(this.name)
