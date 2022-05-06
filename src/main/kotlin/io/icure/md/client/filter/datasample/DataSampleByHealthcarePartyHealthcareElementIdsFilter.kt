package io.icure.md.client.filter.datasample

import io.icure.md.client.filter.Filter
import io.icure.md.client.models.DataSample

data class DataSampleByHealthcarePartyHealthcareElementIdsFilter(
    val healthcarePartyId: String,
    val healthcareElementIds: List<String>,
    override val description: String? = null
) : Filter<DataSample>
