/*
 * Copyright (c) 2020. Taktik SA, All rights reserved.
 */
package io.icure.md.client.filter.datasample

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.icure.kraken.client.models.ServiceDto
import io.icure.md.client.filter.Filter

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class DataSampleByPatientFilter(
    override val description: String? = null,
    val healthcarePartyId: String? = null,
    val patientSecretForeignKeys: Set<String>
) : Filter<ServiceDto>
