/*
 * Copyright (c) 2020. Taktik SA, All rights reserved.
 */
package io.icure.md.client.filter.patient

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.icure.md.client.filter.Filter
import io.icure.md.client.models.Patient

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class PatientByHealthcarePartyGenderEducationProfession(
    override val description: String? = null,
    val healthcarePartyId: String? = null,
    val gender: Patient.Gender? = null,
    val education: String? = null,
    val profession: String? = null
) : Filter<Patient>
