package io.icure.md.client.filter.datasample

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.icure.kraken.client.models.IdentifierDto
import io.icure.md.client.filter.Filter
import io.icure.md.client.models.DataSample

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class DataSampleByHealthcarePartyIdentifiersFilter(
    val healthcarePartyId: String? = null,
    override val description: String? = null,
    val identifiers: List<IdentifierDto> = emptyList()
) : Filter<DataSample>
