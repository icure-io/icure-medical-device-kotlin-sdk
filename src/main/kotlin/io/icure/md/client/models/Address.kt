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
 * the list of addresses (with address type).
 *
 * @param addressType
 * @param description
 * @param street
 * @param houseNumber
 * @param postboxNumber
 * @param postalCode
 * @param city
 * @param state
 * @param country
 * @param note
 * @param telecoms
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class Address(

    /* ktlint-disable enum-entry-name-case */

    @field:JsonProperty("addressType")
    val addressType: Address.AddressType? = null,

    @field:JsonProperty("description")
    val description: kotlin.String? = null,

    @field:JsonProperty("street")
    val street: kotlin.String? = null,

    @field:JsonProperty("houseNumber")
    val houseNumber: kotlin.String? = null,

    @field:JsonProperty("postboxNumber")
    val postboxNumber: kotlin.String? = null,

    @field:JsonProperty("postalCode")
    val postalCode: kotlin.String? = null,

    @field:JsonProperty("city")
    val city: kotlin.String? = null,

    @field:JsonProperty("state")
    val state: kotlin.String? = null,

    @field:JsonProperty("country")
    val country: kotlin.String? = null,

    @field:JsonProperty("note")
    val note: kotlin.String? = null,

    @field:JsonProperty("telecoms")
    val telecoms: kotlin.collections.List<Telecom> = emptyList()

) {

    /**
     *
     *
     * Values: home,work,vacation,hospital,clinic,hq,other,temporary,postal,diplomatic,reference
     */
    enum class AddressType(val value: kotlin.String) {
        @JsonProperty(value = "home")
        home("home"),

        @JsonProperty(value = "work")
        work("work"),

        @JsonProperty(value = "vacation")
        vacation("vacation"),

        @JsonProperty(value = "hospital")
        hospital("hospital"),

        @JsonProperty(value = "clinic")
        clinic("clinic"),

        @JsonProperty(value = "hq")
        hq("hq"),

        @JsonProperty(value = "other")
        other("other"),

        @JsonProperty(value = "temporary")
        temporary("temporary"),

        @JsonProperty(value = "postal")
        postal("postal"),

        @JsonProperty(value = "diplomatic")
        diplomatic("diplomatic"),

        @JsonProperty(value = "reference")
        reference("reference");
    }

    /* ktlint-enable enum-entry-name-case */
}
