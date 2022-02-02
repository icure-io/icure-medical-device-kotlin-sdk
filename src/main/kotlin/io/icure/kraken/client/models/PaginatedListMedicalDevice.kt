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

import io.icure.kraken.client.models.MedicalDevice
import io.icure.kraken.client.models.PaginatedDocumentKeyAndIdPairObject

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder


/**
 * 
 *
 * @param pageSize 
 * @param totalSize 
 * @param rows 
 * @param nextKeyPair 
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class PaginatedListMedicalDevice (

    @field:JsonProperty("pageSize")
    val pageSize: kotlin.Int,

    @field:JsonProperty("totalSize")
    val totalSize: kotlin.Int,

    @field:JsonProperty("rows")
    val rows: kotlin.collections.List<MedicalDevice> = emptyList(),

    @field:JsonProperty("nextKeyPair")
    val nextKeyPair: PaginatedDocumentKeyAndIdPairObject? = null

)

