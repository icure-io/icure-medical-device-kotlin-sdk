package io.icure.md.client.mappers

import io.icure.kraken.client.models.PatientDto
import io.icure.md.client.models.Patient

fun PatientDto.toPatient() = Patient(
    id = this.id,
            identifier = this.identifier.map { it.toIdentifier() },
            labels = this.tags.map { it.toCodingReference() },
            codes = this.codes.map { it.toCodingReference() },
            names = this.names.map { it.toPersonName() },
            languages = this.languages,
            addresses = this.addresses.map { it.toAddress() },
            mergedIds = this.mergedIds,
            active = this.active,
            deactivationReason = this.deactivationReason.toDeactivationReason(),
            partnerships = this.partnerships.map { it.toPartnership() },
            patientHealthCareParties = this.patientHealthCareParties.map { it.toPatientHealthCareParty() },
            patientProfessions = this.patientProfessions.map { it.toCodingReference() },
            parameters = this.parameters,
            properties = this.properties.map { it.toProperty() },
            rev = this.rev,
            created = this.created,
            modified = this.modified,
            author = this.author,
            responsible = this.responsible,
            endOfLife = this.endOfLife,
            deletionDate = this.deletionDate,
            firstName = this.firstName,
            lastName = this.lastName,
            companyName = this.companyName,
            civility = this.civility,
            gender = this.gender?.toGender(),
            birthSex = this.birthSex?.toBirthSex(),
            mergeToPatientId = this.mergeToPatientId,
            alias = this.alias,
            ssin = this.ssin,
            maidenName = this.maidenName,
            spouseName = this.spouseName,
            partnerName = this.partnerName,
            personalStatus = this.personalStatus?.toPersonalStatus(),
            dateOfBirth = this.dateOfBirth,
            dateOfDeath = this.dateOfDeath,
            placeOfBirth = this.placeOfBirth,
            placeOfDeath = this.placeOfDeath,
            deceased = this.deceased,
            education = this.education,
            profession = this.profession,
            note = this.note,
            administrativeNote = this.administrativeNote,
            nationality = this.nationality,
            race = this.race,
            ethnicity = this.ethnicity,
            picture = this.picture,
            externalId = this.externalId,

)

private fun PatientDto.DeactivationReason.toDeactivationReason() = Patient.DeactivationReason.valueOf(this.name)
private fun PatientDto.Gender.toGender() = Patient.Gender.valueOf(this.name)
private fun PatientDto.BirthSex.toBirthSex() = Patient.BirthSex.valueOf(this.name)
private fun PatientDto.PersonalStatus.toPersonalStatus() = Patient.PersonalStatus.valueOf(this.name)
