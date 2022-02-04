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
 * @param id the Id of the healthcare party. We encourage using either a v4 UUID or a HL7 Id.
 * @param rev the revision of the healthcare party in the database, used for conflict management / optimistic locking.
 * @param created creation timestamp of the object.
 * @param modified last modification timestamp of the object.
 * @param deletionDate the soft delete timestamp. When a user is ”deleted“, this is set to a non null value: the moment of the deletion
 * @param name The full name of the healthcare party, used mainly when the healthcare party is an organization
 * @param lastName the lastname (surname) of the healthcare party. This is the official lastname that should be used for official administrative purposes.
 * @param firstName the firstname (name) of the healthcare party.
 * @param names the list of all names of the healthcare party, also containing the official full name information. Ordered by preference of use. First element is therefore the official name used for the healthcare party in the application
 * @param gender the gender of the healthcare party: male, female, indeterminate, changed, changedToMale, changedToFemale, unknown
 * @param civility Mr., Ms., Pr., Dr. ...
 * @param speciality Medical specialty of the healthcare party
 * @param parentId Id of parent of the user representing the healthcare party.
 * @param addresses The list of addresses (with address type).
 * @param languages The list of languages spoken by the patient ordered by fluency (alpha-2 code http://www.loc.gov/standards/iso639-2/ascii_8bits.html).
 * @param picture A picture usually saved in JPEG format.
 * @param specialityCodes Medical specialty of the healthcare party codified using FHIR or Kmehr codificaiton scheme
 * @param notes Text notes.
 * @param properties 
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class HealthcareProfessional (

    /* the Id of the healthcare party. We encourage using either a v4 UUID or a HL7 Id. */
    @field:JsonProperty("id")
    val id: kotlin.String? = null,

    /* the revision of the healthcare party in the database, used for conflict management / optimistic locking. */
    @field:JsonProperty("rev")
    val rev: kotlin.String? = null,

    /* creation timestamp of the object. */
    @field:JsonProperty("created")
    val created: kotlin.Long? = null,

    /* last modification timestamp of the object. */
    @field:JsonProperty("modified")
    val modified: kotlin.Long? = null,

    /* the soft delete timestamp. When a user is ”deleted“, this is set to a non null value: the moment of the deletion */
    @field:JsonProperty("deletionDate")
    val deletionDate: kotlin.Long? = null,

    /* The full name of the healthcare party, used mainly when the healthcare party is an organization */
    @field:JsonProperty("name")
    val name: kotlin.String? = null,

    /* the lastname (surname) of the healthcare party. This is the official lastname that should be used for official administrative purposes. */
    @field:JsonProperty("lastName")
    val lastName: kotlin.String? = null,

    /* the firstname (name) of the healthcare party. */
    @field:JsonProperty("firstName")
    val firstName: kotlin.String? = null,

    /* the list of all names of the healthcare party, also containing the official full name information. Ordered by preference of use. First element is therefore the official name used for the healthcare party in the application */
    @field:JsonProperty("names")
    val names: kotlin.collections.List<PersonName> = emptyList(),

    /* the gender of the healthcare party: male, female, indeterminate, changed, changedToMale, changedToFemale, unknown */
    @field:JsonProperty("gender")
    val gender: HealthcareProfessional.Gender? = null,

    /* Mr., Ms., Pr., Dr. ... */
    @field:JsonProperty("civility")
    val civility: kotlin.String? = null,

    /* Medical specialty of the healthcare party */
    @field:JsonProperty("speciality")
    val speciality: kotlin.String? = null,

    /* Id of parent of the user representing the healthcare party. */
    @field:JsonProperty("parentId")
    val parentId: kotlin.String? = null,

    /* The list of addresses (with address type). */
    @field:JsonProperty("addresses")
    val addresses: kotlin.collections.List<Address> = emptyList(),

    /* The list of languages spoken by the patient ordered by fluency (alpha-2 code http://www.loc.gov/standards/iso639-2/ascii_8bits.html). */
    @field:JsonProperty("languages")
    val languages: kotlin.collections.List<kotlin.String> = emptyList(),

    /* A picture usually saved in JPEG format. */
    @field:JsonProperty("picture")
    val picture: io.icure.kraken.client.infrastructure.ByteArrayWrapper? = null,

    /* Medical specialty of the healthcare party codified using FHIR or Kmehr codificaiton scheme */
    @field:JsonProperty("specialityCodes")
    val specialityCodes: kotlin.collections.List<CodingReference> = emptyList(),

    /* Text notes. */
    @field:JsonProperty("notes")
    val notes: kotlin.String? = null,

    @field:JsonProperty("properties")
    val properties: kotlin.collections.List<Property> = emptyList()

) {

    /**
     * the gender of the healthcare party: male, female, indeterminate, changed, changedToMale, changedToFemale, unknown
     *
     * Values: m,f,i,c,y,x,u
     */
    enum class Gender(val value: kotlin.String) {
        @JsonProperty(value = "M") m("M"),
        @JsonProperty(value = "F") f("F"),
        @JsonProperty(value = "I") i("I"),
        @JsonProperty(value = "C") c("C"),
        @JsonProperty(value = "Y") y("Y"),
        @JsonProperty(value = "X") x("X"),
        @JsonProperty(value = "U") u("U");
    }
}

