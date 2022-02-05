/*
 * Copyright (c) 2020. Taktik SA, All rights reserved.
 */

package io.icure.md.client.filter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class IntersectionFilter<O>(
    override val description: String? = null,
    val filters: List<Filter<O>> = emptyList()
) : Filter<O>
