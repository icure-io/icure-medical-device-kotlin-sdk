/*
 * Copyright (c) 2020. Taktik SA, All rights reserved.
 */

package io.icure.md.client.filter.coding

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.icure.md.client.filter.Filter
import io.icure.md.client.models.Coding

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class CodeByRegionTypeLabelFilter(
    override val description: String? = null,
    val region: String? = null,
    val type: String? = null,
    val language: String? = null,
    val label: String? = null
) : Filter<Coding>
