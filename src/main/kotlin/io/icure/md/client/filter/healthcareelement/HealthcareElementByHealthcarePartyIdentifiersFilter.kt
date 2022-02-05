package io.icure.md.client.filter.healthcareelement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.icure.kraken.client.models.HealthElementDto
import io.icure.kraken.client.models.IdentifierDto
import io.icure.md.client.filter.Filter

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class HealthcareElementByHealthcarePartyIdentifiersFilter(
    override val description: String? = null,
    val healthcarePartyId: String? = null,
    val identifiers: List<IdentifierDto> = emptyList(),
) : Filter<HealthElementDto>
