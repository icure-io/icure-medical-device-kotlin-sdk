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
 * Links (usually for therapeutic reasons) between this patient and healthcare parties (of class PatientHealthcareParty).
 *
 * @param type 
 * @param healthcarePartyId 
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class PatientHealthCareParty (

    @field:JsonProperty("type")
    val type: PatientHealthCareParty.Type,

    @field:JsonProperty("healthcarePartyId")
    val healthcarePartyId: kotlin.String? = null

) {

    /**
     * 
     *
     * Values: doctor,referral,medicalhouse,retirementhome,hospital,other,referringphysician,managingorganization
     */
    enum class Type(val value: kotlin.String) {
        @JsonProperty(value = "doctor") doctor("doctor"),
        @JsonProperty(value = "referral") referral("referral"),
        @JsonProperty(value = "medicalhouse") medicalhouse("medicalhouse"),
        @JsonProperty(value = "retirementhome") retirementhome("retirementhome"),
        @JsonProperty(value = "hospital") hospital("hospital"),
        @JsonProperty(value = "other") other("other"),
        @JsonProperty(value = "referringphysician") referringphysician("referringphysician"),
        @JsonProperty(value = "managingorganization") managingorganization("managingorganization");
    }
}

