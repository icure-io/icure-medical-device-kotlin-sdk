/*
 * Copyright (c) 2020. Taktik SA, All rights reserved.
 */
package io.icure.md.client.filter.healthcareelement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.icure.md.client.filter.Filter
import io.icure.md.client.models.DataSample
import io.icure.md.client.models.HealthcareElement

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class HealthcareElementByHealthcarePartyPatientFilter(
    override val description: String? = null,
    val healthcarePartyId: String? = null,
    val patientSecretForeignKeys: Set<String>
) : Filter<HealthcareElement>
