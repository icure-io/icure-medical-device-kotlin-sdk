
# Patient

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | the Id of the patient. We encourage using either a v4 UUID or a HL7 Id. | 
**identifier** | [**kotlin.collections.List&lt;Identifier&gt;**](Identifier.md) |  | 
**labels** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  | 
**codes** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  | 
**names** | [**kotlin.collections.List&lt;PersonName&gt;**](PersonName.md) | the list of all names of the patient, also containing the official full name information. Ordered by preference of use. First element is therefore the official name used for the patient in the application | 
**languages** | **kotlin.collections.List&lt;kotlin.String&gt;** | the list of languages spoken by the patient ordered by fluency (alpha-2 code http://www.loc.gov/standards/iso639-2/ascii_8bits.html). | 
**addresses** | [**kotlin.collections.List&lt;Address&gt;**](Address.md) | the list of addresses (with address type). | 
**mergedIds** | **kotlin.collections.Set&lt;kotlin.String&gt;** | The ids of the patients that have been merged inside this patient. | 
**active** | **kotlin.Boolean** | Is the patient active (boolean). | 
**deactivationReason** | [**inline**](#DeactivationReasonEnum) | When not active, the reason for deactivation. | 
**partnerships** | [**kotlin.collections.List&lt;Partnership&gt;**](Partnership.md) | List of partners, or persons of contact (of class Partnership, see below). | 
**patientHealthCareParties** | [**kotlin.collections.List&lt;PatientHealthCareParty&gt;**](PatientHealthCareParty.md) | Links (usually for therapeutic reasons) between this patient and healthcare parties (of class PatientHealthcareParty). | 
**patientProfessions** | [**kotlin.collections.List&lt;CodingReference&gt;**](CodingReference.md) | Codified list of professions exercised by this patient. | 
**parameters** | **kotlin.collections.Map&lt;kotlin.String, kotlin.collections.List&lt;kotlin.String&gt;&gt;** | Extra parameters | 
**properties** | [**kotlin.collections.Set&lt;Property&gt;**](Property.md) | Extra properties | 
**rev** | **kotlin.String** | the revision of the patient in the database, used for conflict management / optimistic locking. |  [optional]
**created** | **kotlin.Long** |  |  [optional]
**modified** | **kotlin.Long** |  |  [optional]
**author** | **kotlin.String** |  |  [optional]
**responsible** | **kotlin.String** |  |  [optional]
**endOfLife** | **kotlin.Long** |  |  [optional]
**deletionDate** | **kotlin.Long** |  |  [optional]
**firstName** | **kotlin.String** | the firstname (name) of the patient. |  [optional]
**lastName** | **kotlin.String** | the lastname (surname) of the patient. This is the official lastname that should be used for official administrative purposes. |  [optional]
**companyName** | **kotlin.String** | the name of the company this patient is member of. |  [optional]
**civility** | **kotlin.String** | Mr., Ms., Pr., Dr. ... |  [optional]
**gender** | [**inline**](#GenderEnum) | the gender of the patient: male, female, indeterminate, changed, changedToMale, changedToFemale, unknown |  [optional]
**birthSex** | [**inline**](#BirthSexEnum) | the birth sex of the patient: male, female, indeterminate, unknown |  [optional]
**mergeToPatientId** | **kotlin.String** | The id of the patient this patient has been merged with. |  [optional]
**alias** | **kotlin.String** | An alias of the person, nickname, ... |  [optional]
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


<a name="DeactivationReasonEnum"></a>
## Enum: deactivationReason
Name | Value
---- | -----
deactivationReason | deceased, moved, other_doctor, retired, no_contact, unknown, none


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


<a name="PersonalStatusEnum"></a>
## Enum: personalStatus
Name | Value
---- | -----
personalStatus | single, in_couple, married, separated, divorced, divorcing, widowed, widower, complicated, unknown, contract, other, annulled, polygamous



