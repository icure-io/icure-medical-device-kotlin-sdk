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
 * List of partners, or persons of contact (of class Partnership, see below).
 *
 * @param type 
 * @param status 
 * @param partnerId 
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class Partnership (

    @field:JsonProperty("type")
    val type: Partnership.Type? = null,

    @field:JsonProperty("status")
    val status: Partnership.Status? = null,

    @field:JsonProperty("partnerId")
    val partnerId: kotlin.String? = null

) {

    /**
     * 
     *
     * Values: primaryContact,primaryContactFor,family,friend,counselor,contact,brother,brotherinlaw,child,daughter,employer,father,grandchild,grandparent,husband,lawyer,mother,neighbour,notary,partner,sister,sisterinlaw,son,spouse,stepdaughter,stepfather,stepmother,stepson,tutor,nextOfKin,federalAgency,insuranceCompany,stateAgency,unknown,seealso,refer
     */
    enum class Type(val value: kotlin.String) {
        @JsonProperty(value = "primary_contact") primaryContact("primary_contact"),
        @JsonProperty(value = "primary_contact_for") primaryContactFor("primary_contact_for"),
        @JsonProperty(value = "family") family("family"),
        @JsonProperty(value = "friend") friend("friend"),
        @JsonProperty(value = "counselor") counselor("counselor"),
        @JsonProperty(value = "contact") contact("contact"),
        @JsonProperty(value = "brother") brother("brother"),
        @JsonProperty(value = "brotherinlaw") brotherinlaw("brotherinlaw"),
        @JsonProperty(value = "child") child("child"),
        @JsonProperty(value = "daughter") daughter("daughter"),
        @JsonProperty(value = "employer") employer("employer"),
        @JsonProperty(value = "father") father("father"),
        @JsonProperty(value = "grandchild") grandchild("grandchild"),
        @JsonProperty(value = "grandparent") grandparent("grandparent"),
        @JsonProperty(value = "husband") husband("husband"),
        @JsonProperty(value = "lawyer") lawyer("lawyer"),
        @JsonProperty(value = "mother") mother("mother"),
        @JsonProperty(value = "neighbour") neighbour("neighbour"),
        @JsonProperty(value = "notary") notary("notary"),
        @JsonProperty(value = "partner") partner("partner"),
        @JsonProperty(value = "sister") sister("sister"),
        @JsonProperty(value = "sisterinlaw") sisterinlaw("sisterinlaw"),
        @JsonProperty(value = "son") son("son"),
        @JsonProperty(value = "spouse") spouse("spouse"),
        @JsonProperty(value = "stepdaughter") stepdaughter("stepdaughter"),
        @JsonProperty(value = "stepfather") stepfather("stepfather"),
        @JsonProperty(value = "stepmother") stepmother("stepmother"),
        @JsonProperty(value = "stepson") stepson("stepson"),
        @JsonProperty(value = "tutor") tutor("tutor"),
        @JsonProperty(value = "next_of_kin") nextOfKin("next_of_kin"),
        @JsonProperty(value = "federal_agency") federalAgency("federal_agency"),
        @JsonProperty(value = "insurance_company") insuranceCompany("insurance_company"),
        @JsonProperty(value = "state_agency") stateAgency("state_agency"),
        @JsonProperty(value = "unknown") unknown("unknown"),
        @JsonProperty(value = "seealso") seealso("seealso"),
        @JsonProperty(value = "refer") refer("refer");
    }
    /**
     * 
     *
     * Values: active,complicated,past
     */
    enum class Status(val value: kotlin.String) {
        @JsonProperty(value = "active") active("active"),
        @JsonProperty(value = "complicated") complicated("complicated"),
        @JsonProperty(value = "past") past("past");
    }
}

