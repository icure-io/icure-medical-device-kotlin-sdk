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
 * Extra properties for the user. Those properties are typed (see class Property)
 *
 * @param id
 * @param type
 * @param typedValue
 * @param deleted
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class Property(

    @field:JsonProperty("id")
    val id: kotlin.String? = null,

    @field:JsonProperty("type")
    val type: PropertyType? = null,

    @field:JsonProperty("typedValue")
    val typedValue: TypedValueObject? = null,

    @field:JsonProperty("deleted")
    val deleted: kotlin.Long? = null

)
