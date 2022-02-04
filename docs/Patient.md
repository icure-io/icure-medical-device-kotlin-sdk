
# Patient

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | the Id of the patient. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**rev** | **kotlin.String** | the revision of the patient in the database, used for conflict management / optimistic locking. |  [optional]
**identifiers** | [**kotlin.collections.List&lt;Identifier&gt;**](Identifier.md) | Typically used for business / client identifiers. An identifier should identify a patient uniquely and unambiguously. However, iCure can&#39;t guarantee the uniqueness of those identifiers : This is something you need to take care of. | 
**created** | **kotlin.Long** | the creation date of the patient (encoded as epoch). |  [optional]
**modified** | **kotlin.Long** | the last modification date of the patient (encoded as epoch). |  [optional]
**author** | **kotlin.String** | The id of the [User] that created this patient. When creating the patient, this field will be filled automatically by the current user id if not provided. |  [optional]
**responsible** | **kotlin.String** | The id of the data owner that is responsible of this patient. When creating the patient, will be filled automatically by the current user data owner id ([HealthcareProfessional], [Patient] or [MedicalDevice]) if missing |  [optional]
**labels** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) | A label is an item from a codification system that qualifies a patient as being member of a certain class, whatever the value it might have taken. If the label qualifies the content of a field, it means that whatever the content of the field, the label will always apply. LOINC is a codification system typically used for labels. | 
**codes** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) | A code is an item from a codification system that qualifies the content of this patient. | 
**endOfLife** | **kotlin.Long** | Soft delete (unix epoch in ms) timestamp of the patient |  [optional]
**deletionDate** | **kotlin.Long** | the soft delete timestamp. When a patient is ”deleted“, this is set to a non null value: the moment of the deletion |  [optional]
**firstName** | **kotlin.String** | the firstname (name) of the patient. |  [optional]
**lastName** | **kotlin.String** | the lastname (surname) of the patient. This is the official lastname that should be used for official administrative purposes. |  [optional]
**names** | [**kotlin.collections.List&lt;PersonName&gt;**](PersonName.md) | the list of all names of the patient, also containing the official full name information. Ordered by preference of use. First element is therefore the official name used for the patient in the application | 
**companyName** | **kotlin.String** | the name of the company this patient is member of. |  [optional]
**languages** | **kotlin.collections.List&lt;kotlin.String&gt;** | the list of languages spoken by the patient ordered by fluency (alpha-2 code http://www.loc.gov/standards/iso639-2/ascii_8bits.html). | 
**addresses** | [**kotlin.collections.List&lt;Address&gt;**](Address.md) | the list of addresses (with address type). | 
**civility** | **kotlin.String** | Mr., Ms., Pr., Dr. ... |  [optional]
**gender** | [**inline**](#GenderEnum) | the gender of the patient: male, female, indeterminate, changed, changedToMale, changedToFemale, unknown |  [optional]
**birthSex** | [**inline**](#BirthSexEnum) | the birth sex of the patient: male, female, indeterminate, unknown |  [optional]
**mergeToPatientId** | **kotlin.String** | The id of the patient this patient has been merged with. |  [optional]
**mergedIds** | **kotlin.collections.Set&lt;kotlin.String&gt;** | The ids of the patients that have been merged inside this patient. | 
**alias** | **kotlin.String** | An alias of the person, nickname, ... |  [optional]
**active** | **kotlin.Boolean** | Is the patient active (boolean). | 
**deactivationReason** | [**inline**](#DeactivationReasonEnum) | When not active, the reason for deactivation. | 
**ssin** | **kotlin.String** | Social security inscription number. |  [optional]
**maidenName** | **kotlin.String** | Lastname at birth (can be different of the current name), depending on the country, must be used to design the patient . |  [optional]
**spouseName** | **kotlin.String** | Lastname of the spouse for a married woman, depending on the country, can be used to design the patient. |  [optional]
**partnerName** | **kotlin.String** | Lastname of the partner, should not be used to design the patient. |  [optional]
**personalStatus** | [**inline**](#PersonalStatusEnum) | any of &#x60;single&#x60;, &#x60;in_couple&#x60;, &#x60;married&#x60;, &#x60;separated&#x60;, &#x60;divorced&#x60;, &#x60;divorcing&#x60;, &#x60;widowed&#x60;, &#x60;widower&#x60;, &#x60;complicated&#x60;, &#x60;unknown&#x60;, &#x60;contract&#x60;, &#x60;other&#x60;. |  [optional]
**dateOfBirth** | **kotlin.Int** | The birthdate encoded as a fuzzy date on 8 positions (YYYYMMDD) MM and/or DD can be set to 00 if unknown (19740000 is a valid date). |  [optional]
**dateOfDeath** | **kotlin.Int** | The date of death encoded as a fuzzy date on 8 positions (YYYYMMDD) MM and/or DD can be set to 00 if unknown (19740000 is a valid date). |  [optional]
**placeOfBirth** | **kotlin.String** | The place of birth. |  [optional]
**placeOfDeath** | **kotlin.String** | The place of death. |  [optional]
**deceased** | **kotlin.Boolean** | Is the patient deceased. |  [optional]
**education** | **kotlin.String** | The level of education (college degree, undergraduate, phd). |  [optional]
**profession** | **kotlin.String** | The current professional activity. |  [optional]
**note** | **kotlin.String** | A text note (can be confidential, encrypted by default). |  [optional]
**administrativeNote** | **kotlin.String** | An administrative note, not confidential. |  [optional]
**nationality** | **kotlin.String** | The nationality of the patient. |  [optional]
**race** | **kotlin.String** | The race of the patient. |  [optional]
**ethnicity** | **kotlin.String** | The ethnicity of the patient. |  [optional]
**picture** | [**io.icure.kraken.client.infrastructure.ByteArrayWrapper**](io.icure.kraken.client.infrastructure.ByteArrayWrapper.md) | A picture usually saved in JPEG format. |  [optional]
**externalId** | **kotlin.String** | An external (from another source) id with no guarantee or requirement for unicity . |  [optional]
**partnerships** | [**kotlin.collections.List&lt;Partnership&gt;**](Partnership.md) | List of partners, or persons of contact (of class Partnership, see below). | 
**patientHealthCareParties** | [**kotlin.collections.List&lt;PatientHealthCareParty&gt;**](PatientHealthCareParty.md) | Links (usually for therapeutic reasons) between this patient and healthcare parties (of class PatientHealthcareParty). | 
**patientProfessions** | [**kotlin.collections.List&lt;CodingReference&gt;**](CodingReference.md) | Codified list of professions exercised by this patient. | 
**parameters** | **kotlin.collections.Map&lt;kotlin.String, kotlin.collections.List&lt;kotlin.String&gt;&gt;** | Extra parameters | 
**properties** | [**kotlin.collections.Set&lt;Property&gt;**](Property.md) | Extra properties | 


<a name="GenderEnum"></a>
## Enum: gender
Name | Value
---- | -----
gender | M, F, I, C, Y, X, U


<a name="BirthSexEnum"></a>
## Enum: birthSex
Name | Value
---- | -----
birthSex | M, F, I, C, Y, X, U


<a name="DeactivationReasonEnum"></a>
## Enum: deactivationReason
Name | Value
---- | -----
deactivationReason | deceased, moved, other_doctor, retired, no_contact, unknown, none


<a name="PersonalStatusEnum"></a>
## Enum: personalStatus
Name | Value
---- | -----
personalStatus | single, in_couple, married, separated, divorced, divorcing, widowed, widower, complicated, unknown, contract, other, annulled, polygamous



