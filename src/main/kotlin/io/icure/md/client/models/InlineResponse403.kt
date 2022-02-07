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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.pozo.KotlinBuilder

/**
 *
 *
 * @param short
 * @param char
 * @param int
 * @param long
 * @param float
 * @param double
 * @param direct
 * @param readOnly
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class InlineResponse403(

    @field:JsonProperty("short")
    val short: kotlin.Int? = null,

    @field:JsonProperty("char")
    val char: kotlin.String? = null,

    @field:JsonProperty("int")
    val int: kotlin.Int? = null,

    @field:JsonProperty("long")
    val long: kotlin.Long? = null,

    @field:JsonProperty("float")
    val float: kotlin.Float? = null,

    @field:JsonProperty("double")
    val double: kotlin.Double? = null,

    @field:JsonProperty("direct")
    val direct: kotlin.Boolean? = null,

    @field:JsonProperty("readOnly")
    val readOnly: kotlin.Boolean? = null

)

