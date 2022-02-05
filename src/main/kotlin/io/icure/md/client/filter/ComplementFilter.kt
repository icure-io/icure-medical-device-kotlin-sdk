/*
 * Copyright (c) 2020. Taktik SA, All rights reserved.
 */

package io.icure.md.client.filter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ComplementFilter<O>(
    override val description: String? = null,
    val superSet: Filter<O>,
    val subSet: Filter<O>
) : Filter<O>
