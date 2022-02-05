package io.icure.md.client.filter.patient

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.icure.md.client.filter.Filter
import io.icure.md.client.models.Identifier
import io.icure.md.client.models.Patient

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class PatientByHealthcarePartyAndIdentifiersFilter(
    override val description: String? = null,
    val healthcarePartyId: String? = null,
    val identifiers: List<Identifier> = emptyList(),
) : Filter<Patient>
