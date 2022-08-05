package io.icure.md.client.filter.healthcareelement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.icure.md.client.filter.Filter
import io.icure.md.client.models.HealthcareElement
import io.icure.md.client.models.Identifier

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class HealthcareElementByHealthcarePartyIdentifiersFilter(
    override val description: String? = null,
    val healthcarePartyId: String? = null,
    val identifiers: List<Identifier> = emptyList()
) : Filter<HealthcareElement>
