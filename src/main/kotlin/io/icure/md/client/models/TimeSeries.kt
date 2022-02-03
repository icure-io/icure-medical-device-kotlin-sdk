/**
 * ICure Medical Device Micro Service
 *
 * ICure Medical Device Micro Service
 *
 * The version of the OpenAPI document: v2
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */
package io.icure.md.client.models


import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder


/**
 * A high frequency time-series containing the ts in ms from the start (double) and the values
 *
 * @param fields 
 * @param samples 
 * @param min 
 * @param max 
 * @param mean 
 * @param median 
 * @param variance 
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class TimeSeries (

    @field:JsonProperty("fields")
    val fields: kotlin.collections.List<kotlin.String> = emptyList(),

    @field:JsonProperty("samples")
    val samples: kotlin.collections.List<kotlin.collections.List<kotlin.Double>> = emptyList(),

    @field:JsonProperty("min")
    val min: kotlin.collections.List<kotlin.Double> = emptyList(),

    @field:JsonProperty("max")
    val max: kotlin.collections.List<kotlin.Double> = emptyList(),

    @field:JsonProperty("mean")
    val mean: kotlin.collections.List<kotlin.Double> = emptyList(),

    @field:JsonProperty("median")
    val median: kotlin.collections.List<kotlin.Double> = emptyList(),

    @field:JsonProperty("variance")
    val variance: kotlin.collections.List<kotlin.Double> = emptyList()

)

