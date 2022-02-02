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
import io.icure.kraken.client.models.DataSample

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder


/**
 * 
 *
 * @param id the Id of the batch. We encourage using either a v4 UUID or a HL7 Id.
 * @param labels 
 * @param codes 
 * @param services Set of all services provided to the patient during the batch.
 * @param rev the revision of the batch in the database, used for conflict management / optimistic locking.
 * @param created 
 * @param modified 
 * @param author 
 * @param responsible 
 * @param endOfLife 
 * @param deletionDate 
 * @param groupId Separate batches can merged in one logical batch if they share the same groupId. When a batch must be split to selectively assign rights to healthcare parties, the split batches all share the same groupId
 * @param openingDate The date (YYYYMMDDhhmmss) of the start of the batch.
 * @param closingDate The date (YYYYMMDDhhmmss) marking the end of the batch.
 * @param description Description of the batch
 * @param location Location where the batch was recorded.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class Batch (

    /* the Id of the batch. We encourage using either a v4 UUID or a HL7 Id. */
    @field:JsonProperty("id")
    val id: kotlin.String,

    @field:JsonProperty("labels")
    val labels: kotlin.collections.List<CodingReference> = emptyList(),

    @field:JsonProperty("codes")
    val codes: kotlin.collections.List<CodingReference> = emptyList(),

    /* Set of all services provided to the patient during the batch. */
    @field:JsonProperty("services")
    val services: kotlin.collections.List<DataSample> = emptyList(),

    /* the revision of the batch in the database, used for conflict management / optimistic locking. */
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

    @field:JsonProperty("endOfLife")
    val endOfLife: kotlin.Long? = null,

    @field:JsonProperty("deletionDate")
    val deletionDate: kotlin.Long? = null,

    /* Separate batches can merged in one logical batch if they share the same groupId. When a batch must be split to selectively assign rights to healthcare parties, the split batches all share the same groupId */
    @field:JsonProperty("groupId")
    val groupId: kotlin.String? = null,

    /* The date (YYYYMMDDhhmmss) of the start of the batch. */
    @field:JsonProperty("openingDate")
    val openingDate: kotlin.Long? = null,

    /* The date (YYYYMMDDhhmmss) marking the end of the batch. */
    @field:JsonProperty("closingDate")
    val closingDate: kotlin.Long? = null,

    /* Description of the batch */
    @field:JsonProperty("description")
    val description: kotlin.String? = null,

    /* Location where the batch was recorded. */
    @field:JsonProperty("location")
    val location: kotlin.String? = null

)

