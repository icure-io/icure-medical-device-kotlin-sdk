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
 * @param id The Id of the healthcare element. We encourage using either a v4 UUID or a HL7 Id.
 * @param identifiers Typically used for business / client identifiers. An identifier should identify a data sample uniquely and unambiguously. However, iCure can't guarantee the uniqueness of those identifiers : This is something you need to take care of.
 * @param rev The revision of the healthcare element in the database, used for conflict management / optimistic locking.
 * @param created The timestamp (unix epoch in ms) of creation of this healthcare element in iCure system. Will be filled automatically if not provided.
 * @param modified The timestamp (unix epoch in ms) of the latest modification of this healthcare element in iCure system. Will be filled automatically if not provided.
 * @param author The id of the [User] that created this healthcare element. When creating the healthcare element, will be filled automatically by the current user id if not provided.
 * @param responsible The id of the data owner that is responsible of this healthcare element. When creating the healthcare element, will be filled automatically by the current user data owner id ([HealthcareProfessional], [Patient] or [MedicalDevice]) if missing
 * @param medicalLocationId
 * @param tags
 * @param codes A code is an item from a codification system that qualifies the content of this healthcare element. SNOMED-CT, ICPC-2 or ICD-10 codifications systems can be used for codes
 * @param endOfLife Soft delete (unix epoch in ms) timestamp of the healthcare element
 * @param deletionDate the soft delete timestamp. When a healthcare element is ”deleted“, this is set to a non null value: the moment of the deletion
 * @param healthElementId The logical id of the healthcare element, used to link together different versions of the same healthcare element. We encourage using either a v4 UUID or a HL7 Id.
 * @param valueDate The date (unix epoch in ms) when the healthcare element is noted to have started and also closes on the same date
 * @param openingDate The date (unix epoch in ms) of the start of the healthcare element.
 * @param closingDate The date (unix epoch in ms) marking the end of the healthcare element.
 * @param description Description of the healthcare element.
 * @param note A text note (can be confidential, encrypted by default).
 * @param systemMetaData
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class HealthcareElement(

    /* The Id of the healthcare element. We encourage using either a v4 UUID or a HL7 Id. */
    @field:JsonProperty("id")
    val id: kotlin.String? = null,

    /* Typically used for business / client identifiers. An identifier should identify a data sample uniquely and unambiguously. However, iCure can't guarantee the uniqueness of those identifiers : This is something you need to take care of. */
    @field:JsonProperty("identifiers")
    val identifiers: kotlin.collections.List<Identifier> = emptyList(),

    /* The revision of the healthcare element in the database, used for conflict management / optimistic locking. */
    @field:JsonProperty("rev")
    val rev: kotlin.String? = null,

    /* The timestamp (unix epoch in ms) of creation of this healthcare element in iCure system. Will be filled automatically if not provided. */
    @field:JsonProperty("created")
    val created: kotlin.Long? = null,

    /* The timestamp (unix epoch in ms) of the latest modification of this healthcare element in iCure system. Will be filled automatically if not provided. */
    @field:JsonProperty("modified")
    val modified: kotlin.Long? = null,

    /* The id of the [User] that created this healthcare element. When creating the healthcare element, will be filled automatically by the current user id if not provided. */
    @field:JsonProperty("author")
    val author: kotlin.String? = null,

    /* The id of the data owner that is responsible of this healthcare element. When creating the healthcare element, will be filled automatically by the current user data owner id ([HealthcareProfessional], [Patient] or [MedicalDevice]) if missing */
    @field:JsonProperty("responsible")
    val responsible: kotlin.String? = null,

    @field:JsonProperty("medicalLocationId")
    val medicalLocationId: kotlin.String? = null,

    @field:JsonProperty("tags")
    val tags: kotlin.collections.List<CodingReference> = emptyList(),

    /* A code is an item from a codification system that qualifies the content of this healthcare element. SNOMED-CT, ICPC-2 or ICD-10 codifications systems can be used for codes */
    @field:JsonProperty("codes")
    val codes: kotlin.collections.List<CodingReference> = emptyList(),

    /* Soft delete (unix epoch in ms) timestamp of the healthcare element */
    @field:JsonProperty("endOfLife")
    val endOfLife: kotlin.Long? = null,

    /* the soft delete timestamp. When a healthcare element is ”deleted“, this is set to a non null value: the moment of the deletion */
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
    val note: kotlin.String? = null,

    @field:JsonProperty("systemMetaData")
    val systemMetaData: SystemMetaDataEncrypted? = null

)

