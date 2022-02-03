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

import io.icure.md.client.models.CodingReference
import io.icure.md.client.models.Identifier

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder


/**
 * 
 *
 * @param id The Id of the healthcare element. We encourage using either a v4 UUID or a HL7 Id.
 * @param identifiers 
 * @param tags 
 * @param codes 
 * @param rev The revision of the healthcare element in the database, used for conflict management / optimistic locking.
 * @param created 
 * @param modified 
 * @param author 
 * @param responsible 
 * @param medicalLocationId 
 * @param endOfLife 
 * @param deletionDate 
 * @param healthElementId The logical id of the healthcare element, used to link together different versions of the same healthcare element. We encourage using either a v4 UUID or a HL7 Id.
 * @param valueDate The date (unix epoch in ms) when the healthcare element is noted to have started and also closes on the same date
 * @param openingDate The date (unix epoch in ms) of the start of the healthcare element.
 * @param closingDate The date (unix epoch in ms) marking the end of the healthcare element.
 * @param description Description of the healthcare element.
 * @param note A text note (can be confidential, encrypted by default).
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class HealthcareElement (

    /* The Id of the healthcare element. We encourage using either a v4 UUID or a HL7 Id. */
    @field:JsonProperty("id")
    val id: kotlin.String,

    @field:JsonProperty("identifiers")
    val identifiers: kotlin.collections.List<Identifier> = emptyList(),

    @field:JsonProperty("tags")
    val tags: kotlin.collections.List<CodingReference> = emptyList(),

    @field:JsonProperty("codes")
    val codes: kotlin.collections.List<CodingReference> = emptyList(),

    /* The revision of the healthcare element in the database, used for conflict management / optimistic locking. */
    @field:JsonProperty("rev")
    val rev: kotlin.String? = null,

    @field:JsonProperty("created")
    val created: kotlin.Long? = null,

    @field:JsonProperty("modified")
    val modified: kotlin.Long? = null,

    @field:JsonProperty("author")
    val author: kotlin.String? = null,

    @field:JsonProperty("responsible")
    val responsible: kotlin.String? = null,

    @field:JsonProperty("medicalLocationId")
    val medicalLocationId: kotlin.String? = null,

    @field:JsonProperty("endOfLife")
    val endOfLife: kotlin.Long? = null,

    @field:JsonProperty("deletionDate")
    val deletionDate: kotlin.Long? = null,

    /* The logical id of the healthcare element, used to link together different versions of the same healthcare element. We encourage using either a v4 UUID or a HL7 Id. */
    @field:JsonProperty("healthElementId")
    val healthElementId: kotlin.String? = null,

    /* The date (unix epoch in ms) when the healthcare element is noted to have started and also closes on the same date */
    @field:JsonProperty("valueDate")
    val valueDate: kotlin.Long? = null,

    /* The date (unix epoch in ms) of the start of the healthcare element. */
    @field:JsonProperty("openingDate")
    val openingDate: kotlin.Long? = null,

    /* The date (unix epoch in ms) marking the end of the healthcare element. */
    @field:JsonProperty("closingDate")
    val closingDate: kotlin.Long? = null,

    /* Description of the healthcare element. */
    @field:JsonProperty("description")
    val description: kotlin.String? = null,

    /* A text note (can be confidential, encrypted by default). */
    @field:JsonProperty("note")
    val note: kotlin.String? = null

)

