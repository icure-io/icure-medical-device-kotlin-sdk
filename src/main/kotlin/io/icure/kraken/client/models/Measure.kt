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
package io.icure.kraken.client.models

import io.icure.kraken.client.models.CodingReference

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder


/**
 * 
 *
 * @param `value` 
 * @param min 
 * @param max 
 * @param ref 
 * @param severity 
 * @param severityCode 
 * @param evolution 
 * @param unit 
 * @param unitCodes 
 * @param comment 
 * @param comparator 
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class Measure (

    @field:JsonProperty("value")
    val `value`: kotlin.Double? = null,

    @field:JsonProperty("min")
    val min: kotlin.Double? = null,

    @field:JsonProperty("max")
    val max: kotlin.Double? = null,

    @field:JsonProperty("ref")
    val ref: kotlin.Double? = null,

    @field:JsonProperty("severity")
    val severity: kotlin.Int? = null,

    @field:JsonProperty("severityCode")
    val severityCode: kotlin.String? = null,

    @field:JsonProperty("evolution")
    val evolution: kotlin.Int? = null,

    @field:JsonProperty("unit")
    val unit: kotlin.String? = null,

    @field:JsonProperty("unitCodes")
    val unitCodes: kotlin.collections.Set<CodingReference>? = null,

    @field:JsonProperty("comment")
    val comment: kotlin.String? = null,

    @field:JsonProperty("comparator")
    val comparator: kotlin.String? = null

)

