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
 * @param id the Id of the patient. We encourage using either a v4 UUID or a HL7 Id.
 * @param rev the revision of the patient in the database, used for conflict management / optimistic locking.
 * @param identifier 
 * @param created 
 * @param modified 
 * @param author 
 * @param responsible 
 * @param labels 
 * @param codes 
 * @param endOfLife 
 * @param deletionDate 
 * @param firstName the firstname (name) of the patient.
 * @param lastName the lastname (surname) of the patient. This is the official lastname that should be used for official administrative purposes.
 * @param names the list of all names of the patient, also containing the official full name information. Ordered by preference of use. First element is therefore the official name used for the patient in the application
 * @param companyName the name of the company this patient is member of.
 * @param languages the list of languages spoken by the patient ordered by fluency (alpha-2 code http://www.loc.gov/standards/iso639-2/ascii_8bits.html).
 * @param addresses the list of addresses (with address type).
 * @param civility Mr., Ms., Pr., Dr. ...
 * @param gender the gender of the patient: male, female, indeterminate, changed, changedToMale, changedToFemale, unknown
 * @param birthSex the birth sex of the patient: male, female, indeterminate, unknown
 * @param mergeToPatientId The id of the patient this patient has been merged with.
 * @param mergedIds The ids of the patients that have been merged inside this patient.
 * @param alias An alias of the person, nickname, ...
 * @param active Is the patient active (boolean).
 * @param deactivationReason When not active, the reason for deactivation.
 * @param ssin Social security inscription number.
 * @param maidenName Lastname at birth (can be different of the current name), depending on the country, must be used to design the patient .
 * @param spouseName Lastname of the spouse for a married woman, depending on the country, can be used to design the patient.
 * @param partnerName Lastname of the partner, should not be used to design the patient.
 * @param personalStatus any of `single`, `in_couple`, `married`, `separated`, `divorced`, `divorcing`, `widowed`, `widower`, `complicated`, `unknown`, `contract`, `other`.
 * @param dateOfBirth The birthdate encoded as a fuzzy date on 8 positions (YYYYMMDD) MM and/or DD can be set to 00 if unknown (19740000 is a valid date).
 * @param dateOfDeath The date of death encoded as a fuzzy date on 8 positions (YYYYMMDD) MM and/or DD can be set to 00 if unknown (19740000 is a valid date).
 * @param placeOfBirth The place of birth.
 * @param placeOfDeath The place of death.
 * @param deceased Is the patient deceased.
 * @param education The level of education (college degree, undergraduate, phd).
 * @param profession The current professional activity.
 * @param note A text note (can be confidential, encrypted by default).
 * @param administrativeNote An administrative note, not confidential.
 * @param nationality The nationality of the patient.
 * @param race The race of the patient.
 * @param ethnicity The ethnicity of the patient.
 * @param picture A picture usually saved in JPEG format.
 * @param externalId An external (from another source) id with no guarantee or requirement for unicity .
 * @param partnerships List of partners, or persons of contact (of class Partnership, see below).
 * @param patientHealthCareParties Links (usually for therapeutic reasons) between this patient and healthcare parties (of class PatientHealthcareParty).
 * @param patientProfessions Codified list of professions exercised by this patient.
 * @param parameters Extra parameters
 * @param properties Extra properties
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class Patient (

    /* the Id of the patient. We encourage using either a v4 UUID or a HL7 Id. */
    @field:JsonProperty("id")
    val id: kotlin.String? = null,

    /* the revision of the patient in the database, used for conflict management / optimistic locking. */
    @field:JsonProperty("rev")
    val rev: kotlin.String? = null,

    @field:JsonProperty("identifier")
    val identifier: kotlin.collections.List<Identifier> = emptyList(),

    @field:JsonProperty("created")
    val created: kotlin.Long? = null,

    @field:JsonProperty("modified")
    val modified: kotlin.Long? = null,

    @field:JsonProperty("author")
    val author: kotlin.String? = null,

    @field:JsonProperty("responsible")
    val responsible: kotlin.String? = null,

    @field:JsonProperty("labels")
    val labels: kotlin.collections.List<CodingReference> = emptyList(),

    @field:JsonProperty("codes")
    val codes: kotlin.collections.List<CodingReference> = emptyList(),

    @field:JsonProperty("endOfLife")
    val endOfLife: kotlin.Long? = null,

    @field:JsonProperty("deletionDate")
    val deletionDate: kotlin.Long? = null,

    /* the firstname (name) of the patient. */
    @field:JsonProperty("firstName")
    val firstName: kotlin.String? = null,

    /* the lastname (surname) of the patient. This is the official lastname that should be used for official administrative purposes. */
    @field:JsonProperty("lastName")
    val lastName: kotlin.String? = null,

    /* the list of all names of the patient, also containing the official full name information. Ordered by preference of use. First element is therefore the official name used for the patient in the application */
    @field:JsonProperty("names")
    val names: kotlin.collections.List<PersonName> = emptyList(),

    /* the name of the company this patient is member of. */
    @field:JsonProperty("companyName")
    val companyName: kotlin.String? = null,

    /* the list of languages spoken by the patient ordered by fluency (alpha-2 code http://www.loc.gov/standards/iso639-2/ascii_8bits.html). */
    @field:JsonProperty("languages")
    val languages: kotlin.collections.List<kotlin.String> = emptyList(),

    /* the list of addresses (with address type). */
    @field:JsonProperty("addresses")
    val addresses: kotlin.collections.List<Address> = emptyList(),

    /* Mr., Ms., Pr., Dr. ... */
    @field:JsonProperty("civility")
    val civility: kotlin.String? = null,

    /* the gender of the patient: male, female, indeterminate, changed, changedToMale, changedToFemale, unknown */
    @field:JsonProperty("gender")
    val gender: Patient.Gender? = null,

    /* the birth sex of the patient: male, female, indeterminate, unknown */
    @field:JsonProperty("birthSex")
    val birthSex: Patient.BirthSex? = null,

    /* The id of the patient this patient has been merged with. */
    @field:JsonProperty("mergeToPatientId")
    val mergeToPatientId: kotlin.String? = null,

    /* The ids of the patients that have been merged inside this patient. */
    @field:JsonProperty("mergedIds")
    val mergedIds: kotlin.collections.List<kotlin.String> = emptyList(),

    /* An alias of the person, nickname, ... */
    @field:JsonProperty("alias")
    val alias: kotlin.String? = null,

    /* Is the patient active (boolean). */
    @field:JsonProperty("active")
    val active: kotlin.Boolean = true,

    /* When not active, the reason for deactivation. */
    @field:JsonProperty("deactivationReason")
    val deactivationReason: Patient.DeactivationReason = DeactivationReason.none,

    /* Social security inscription number. */
    @field:JsonProperty("ssin")
    val ssin: kotlin.String? = null,

    /* Lastname at birth (can be different of the current name), depending on the country, must be used to design the patient . */
    @field:JsonProperty("maidenName")
    val maidenName: kotlin.String? = null,

    /* Lastname of the spouse for a married woman, depending on the country, can be used to design the patient. */
    @field:JsonProperty("spouseName")
    val spouseName: kotlin.String? = null,

    /* Lastname of the partner, should not be used to design the patient. */
    @field:JsonProperty("partnerName")
    val partnerName: kotlin.String? = null,

    /* any of `single`, `in_couple`, `married`, `separated`, `divorced`, `divorcing`, `widowed`, `widower`, `complicated`, `unknown`, `contract`, `other`. */
    @field:JsonProperty("personalStatus")
    val personalStatus: Patient.PersonalStatus? = null,

    /* The birthdate encoded as a fuzzy date on 8 positions (YYYYMMDD) MM and/or DD can be set to 00 if unknown (19740000 is a valid date). */
    @field:JsonProperty("dateOfBirth")
    val dateOfBirth: kotlin.Int? = null,

    /* The date of death encoded as a fuzzy date on 8 positions (YYYYMMDD) MM and/or DD can be set to 00 if unknown (19740000 is a valid date). */
    @field:JsonProperty("dateOfDeath")
    val dateOfDeath: kotlin.Int? = null,

    /* The place of birth. */
    @field:JsonProperty("placeOfBirth")
    val placeOfBirth: kotlin.String? = null,

    /* The place of death. */
    @field:JsonProperty("placeOfDeath")
    val placeOfDeath: kotlin.String? = null,

    /* Is the patient deceased. */
    @field:JsonProperty("deceased")
    val deceased: kotlin.Boolean? = null,

    /* The level of education (college degree, undergraduate, phd). */
    @field:JsonProperty("education")
    val education: kotlin.String? = null,

    /* The current professional activity. */
    @field:JsonProperty("profession")
    val profession: kotlin.String? = null,

    /* A text note (can be confidential, encrypted by default). */
    @field:JsonProperty("note")
    val note: kotlin.String? = null,

    /* An administrative note, not confidential. */
    @field:JsonProperty("administrativeNote")
    val administrativeNote: kotlin.String? = null,

    /* The nationality of the patient. */
    @field:JsonProperty("nationality")
    val nationality: kotlin.String? = null,

    /* The race of the patient. */
    @field:JsonProperty("race")
    val race: kotlin.String? = null,

    /* The ethnicity of the patient. */
    @field:JsonProperty("ethnicity")
    val ethnicity: kotlin.String? = null,

    /* A picture usually saved in JPEG format. */
    @field:JsonProperty("picture")
    val picture: io.icure.kraken.client.infrastructure.ByteArrayWrapper? = null,

    /* An external (from another source) id with no guarantee or requirement for unicity . */
    @field:JsonProperty("externalId")
    val externalId: kotlin.String? = null,

    /* List of partners, or persons of contact (of class Partnership, see below). */
    @field:JsonProperty("partnerships")
    val partnerships: kotlin.collections.List<Partnership> = emptyList(),

    /* Links (usually for therapeutic reasons) between this patient and healthcare parties (of class PatientHealthcareParty). */
    @field:JsonProperty("patientHealthCareParties")
    val patientHealthCareParties: kotlin.collections.List<PatientHealthCareParty> = emptyList(),

    /* Codified list of professions exercised by this patient. */
    @field:JsonProperty("patientProfessions")
    val patientProfessions: kotlin.collections.List<CodingReference> = emptyList(),

    /* Extra parameters */
    @field:JsonProperty("parameters")
    val parameters: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>> = emptyMap(),

    /* Extra properties */
    @field:JsonProperty("properties")
    val properties: kotlin.collections.List<Property> = emptyList()

) {

    /**
     * the gender of the patient: male, female, indeterminate, changed, changedToMale, changedToFemale, unknown
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
    /**
     * the birth sex of the patient: male, female, indeterminate, unknown
     *
     * Values: m,f,i,c,y,x,u
     */
    enum class BirthSex(val value: kotlin.String) {
        @JsonProperty(value = "M") m("M"),
        @JsonProperty(value = "F") f("F"),
        @JsonProperty(value = "I") i("I"),
        @JsonProperty(value = "C") c("C"),
        @JsonProperty(value = "Y") y("Y"),
        @JsonProperty(value = "X") x("X"),
        @JsonProperty(value = "U") u("U");
    }
    /**
     * When not active, the reason for deactivation.
     *
     * Values: deceased,moved,otherDoctor,retired,noContact,unknown,none
     */
    enum class DeactivationReason(val value: kotlin.String) {
        @JsonProperty(value = "deceased") deceased("deceased"),
        @JsonProperty(value = "moved") moved("moved"),
        @JsonProperty(value = "other_doctor") otherDoctor("other_doctor"),
        @JsonProperty(value = "retired") retired("retired"),
        @JsonProperty(value = "no_contact") noContact("no_contact"),
        @JsonProperty(value = "unknown") unknown("unknown"),
        @JsonProperty(value = "none") none("none");
    }
    /**
     * any of `single`, `in_couple`, `married`, `separated`, `divorced`, `divorcing`, `widowed`, `widower`, `complicated`, `unknown`, `contract`, `other`.
     *
     * Values: single,inCouple,married,separated,divorced,divorcing,widowed,widower,complicated,unknown,`contract`,other,annulled,polygamous
     */
    enum class PersonalStatus(val value: kotlin.String) {
        @JsonProperty(value = "single") single("single"),
        @JsonProperty(value = "in_couple") inCouple("in_couple"),
        @JsonProperty(value = "married") married("married"),
        @JsonProperty(value = "separated") separated("separated"),
        @JsonProperty(value = "divorced") divorced("divorced"),
        @JsonProperty(value = "divorcing") divorcing("divorcing"),
        @JsonProperty(value = "widowed") widowed("widowed"),
        @JsonProperty(value = "widower") widower("widower"),
        @JsonProperty(value = "complicated") complicated("complicated"),
        @JsonProperty(value = "unknown") unknown("unknown"),
        @JsonProperty(value = "contract") `contract`("contract"),
        @JsonProperty(value = "other") other("other"),
        @JsonProperty(value = "annulled") annulled("annulled"),
        @JsonProperty(value = "polygamous") polygamous("polygamous");
    }
}

