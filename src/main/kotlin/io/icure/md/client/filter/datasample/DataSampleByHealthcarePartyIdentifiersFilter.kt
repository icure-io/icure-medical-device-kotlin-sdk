package io.icure.md.client.filter.datasample

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.icure.kraken.client.models.IdentifierDto
import io.icure.kraken.client.models.ServiceDto
import io.icure.md.client.filter.Filter

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class DataSampleByHealthcarePartyIdentifiersFilter(
    val healthcarePartyId: String? = null,
    override val description: String? = null,
    val identifiers: List<IdentifierDto> = emptyList()
) : Filter<ServiceDto>
