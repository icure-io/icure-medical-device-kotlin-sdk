/* ktlint-disable filename */
package io.icure.md.client.mappers

import io.icure.kraken.client.models.PatientHealthCarePartyDto
import io.icure.md.client.models.PatientHealthCareParty

fun PatientHealthCarePartyDto.toPatientHealthCareParty() = PatientHealthCareParty(
    type = this.type?.toPatientHealthCarePartyType() ?: PatientHealthCareParty.Type.other,
    healthcarePartyId = this.healthcarePartyId
)

private fun PatientHealthCarePartyDto.Type.toPatientHealthCarePartyType() =
    PatientHealthCareParty.Type.valueOf(this.name)

fun PatientHealthCareParty.toPatientHealthCareParty() = PatientHealthCarePartyDto(
    type = this.type.toPatientHealthCarePartyDto(),
    healthcarePartyId = this.healthcarePartyId
)

private fun PatientHealthCareParty.Type.toPatientHealthCarePartyDto() =
    PatientHealthCarePartyDto.Type.valueOf(this.name)

/* ktlint-enable filename */
