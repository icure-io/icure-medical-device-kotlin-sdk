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
 * @param id 
 * @param identifiers 
 * @param labels 
 * @param codes 
 * @param properties 
 * @param rev 
 * @param deletionDate 
 * @param created 
 * @param modified 
 * @param author 
 * @param responsible 
 * @param endOfLife 
 * @param externalId 
 * @param name 
 * @param type 
 * @param brand 
 * @param model 
 * @param serialNumber 
 * @param parentId 
 * @param picture 
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class MedicalDevice (

    @field:JsonProperty("id")
    val id: String?,

    @field:JsonProperty("identifiers")
    val identifiers: List<Identifier> = emptyList(),

    @field:JsonProperty("labels")
    val labels: List<CodingReference> = emptyList(),

    @field:JsonProperty("codes")
    val codes: List<CodingReference> = emptyList(),

    @field:JsonProperty("properties")
    val properties: List<Property> = emptyList(),

    @field:JsonProperty("rev")
    val rev: String? = null,

    @field:JsonProperty("deletionDate")
    val deletionDate: Long? = null,

    @field:JsonProperty("created")
    val created: Long? = null,

    @field:JsonProperty("modified")
    val modified: Long? = null,

    @field:JsonProperty("author")
    val author: String? = null,

    @field:JsonProperty("responsible")
    val responsible: String? = null,

    @field:JsonProperty("endOfLife")
    val endOfLife: Long? = null,

    @field:JsonProperty("externalId")
    val externalId: String? = null,

    @field:JsonProperty("name")
    val name: String? = null,

    @field:JsonProperty("type")
    val type: String? = null,

    @field:JsonProperty("brand")
    val brand: String? = null,

    @field:JsonProperty("model")
    val model: String? = null,

    @field:JsonProperty("serialNumber")
    val serialNumber: String? = null,

    @field:JsonProperty("parentId")
    val parentId: String? = null,

    @field:JsonProperty("picture")
    val picture: List<io.icure.kraken.client.infrastructure.ByteArrayWrapper>? = null

)

