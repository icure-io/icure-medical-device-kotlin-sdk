package io.icure.md.client.mappers

import io.icure.kraken.client.models.decrypted.PatientDto
import io.icure.md.client.models.Patient
import io.icure.md.client.models.SystemMetaDataOwnerEncrypted
import java.util.*

fun PatientDto.toPatient() = Patient(
    id = this.id,
    identifiers = this.identifier.map { it.toIdentifier() },
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
    publicKey = this.publicKey,
    systemMetaData = SystemMetaDataOwnerEncrypted(
        hcPartyKeys = this.hcPartyKeys,
        privateKeyShamirPartitions = this.privateKeyShamirPartitions,
        secretForeignKeys = this.secretForeignKeys,
        cryptedForeignKeys = this.cryptedForeignKeys.mapValues { (_, v) -> v.map { it.toDelegation() }.toSet() },
        delegations = this.delegations.mapValues { (_, v) -> v.map { it.toDelegation() }.toSet() },
        encryptionKeys = this.encryptionKeys.mapValues { (_, v) -> v.map { it.toDelegation() }.toSet() },
        aesExchangeKeys = this.aesExchangeKeys,
        transferKeys = this.transferKeys,
        lostHcPartyKeys = this.lostHcPartyKeys
    )
)

fun PatientDto.DeactivationReason.toDeactivationReason() = Patient.DeactivationReason.valueOf(this.name)
fun PatientDto.Gender.toGender() = Patient.Gender.valueOf(this.name)
fun PatientDto.BirthSex.toBirthSex() = Patient.BirthSex.valueOf(this.name)
fun PatientDto.PersonalStatus.toPersonalStatus() = Patient.PersonalStatus.valueOf(this.name)

fun Patient.toPatientDto() = PatientDto(
    id = this.id?.also {
        try {
            UUID.fromString(it)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid id, id must be a valid UUID")
        }
    } ?: UUID.randomUUID().toString(),
    identifier = this.identifiers.map { it.toIdentifierDto() },
    tags = this.labels.map { it.toCodeStubDto() },
    codes = this.codes.map { it.toCodeStubDto() },
    names = this.names.map { it.toPersonNameDto() },
    languages = this.languages,
    addresses = this.addresses.map { it.toAddressDto() },
    mergedIds = this.mergedIds,
    active = this.active,
    deactivationReason = this.deactivationReason.toDeactivationReason(),
    partnerships = this.partnerships.map { it.toPartnershipDto() },
    patientHealthCareParties = this.patientHealthCareParties.map { it.toPatientHealthCareParty() },
    patientProfessions = this.patientProfessions.map { it.toCodeStubDto() },
    parameters = this.parameters,
    properties = this.properties.map { it.toPropertyStubDto() },
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
    hcPartyKeys = this.systemMetaData?.hcPartyKeys ?: emptyMap(),
    privateKeyShamirPartitions = this.systemMetaData?.privateKeyShamirPartitions ?: emptyMap(),
    secretForeignKeys = this.systemMetaData?.secretForeignKeys ?: listOf(),
    cryptedForeignKeys = this.systemMetaData?.cryptedForeignKeys?.mapValues { (k, v) ->
        v.map { it.toDelegationDto() }.toSet()
    } ?: emptyMap(),
    delegations = this.systemMetaData?.delegations?.mapValues { (k, v) -> v.map { it.toDelegationDto() }.toSet() }
        ?: emptyMap(),
    encryptionKeys = this.systemMetaData?.encryptionKeys?.mapValues { (k, v) -> v.map { it.toDelegationDto() }.toSet() }
        ?: emptyMap(),
    aesExchangeKeys = this.systemMetaData?.aesExchangeKeys ?: emptyMap(),
    transferKeys = this.systemMetaData?.transferKeys ?: emptyMap(),
    lostHcPartyKeys = this.systemMetaData?.lostHcPartyKeys ?: emptyList(),
    publicKey = this.publicKey
)

fun Patient.DeactivationReason.toDeactivationReason() = PatientDto.DeactivationReason.valueOf(this.name)
fun Patient.Gender.toGender() = PatientDto.Gender.valueOf(this.name)
fun Patient.BirthSex.toBirthSex() = PatientDto.BirthSex.valueOf(this.name)
fun Patient.PersonalStatus.toPersonalStatus() = PatientDto.PersonalStatus.valueOf(this.name)
fun Patient.Gender.toDbGender() = io.icure.kraken.client.models.PatientDto.Gender.valueOf(this.name)
