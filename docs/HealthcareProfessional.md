
# HealthcareProfessional

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | the Id of the healthcare party. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**rev** | **kotlin.String** | the revision of the healthcare party in the database, used for conflict management / optimistic locking. |  [optional]
**created** | **kotlin.Long** | creation timestamp of the object. |  [optional]
**modified** | **kotlin.Long** | last modification timestamp of the object. |  [optional]
**deletionDate** | **
kotlin.Long** | the soft delete timestamp. When a healthcare professional is ”deleted“, this is set to a non null value: the moment of the deletion |  [optional]
**name** | **
kotlin.String** | The full name of the healthcare party, used mainly when the healthcare party is an organization |  [optional]
**lastName** | **kotlin.String** | the lastname (surname) of the healthcare party. This is the official lastname that should be used for official administrative purposes. |  [optional]
**firstName** | **kotlin.String** | the firstname (name) of the healthcare party. |  [optional]
**names** | [**kotlin.collections.List&lt;PersonName&gt;**](PersonName.md) | the list of all names of the healthcare party, also containing the official full name information. Ordered by preference of use. First element is therefore the official name used for the healthcare party in the application | 
**gender** | [**inline**](#GenderEnum) | the gender of the healthcare party: male, female, indeterminate, changed, changedToMale, changedToFemale, unknown |  [optional]
**civility** | **kotlin.String** | Mr., Ms., Pr., Dr. ... |  [optional]
**speciality** | **kotlin.String** | Medical specialty of the healthcare party |  [optional]
**parentId** | **kotlin.String** | Id of parent of the user representing the healthcare party. |  [optional]
**addresses** | [**kotlin.collections.List&lt;Address&gt;**](Address.md) | The list of addresses (with address type). | 
**languages** | **kotlin.collections.List&lt;kotlin.String&gt;** | The list of languages spoken by the patient ordered by fluency (alpha-2 code http://www.loc.gov/standards/iso639-2/ascii_8bits.html). | 
**picture** | [**io.icure.kraken.client.infrastructure.ByteArrayWrapper**](io.icure.kraken.client.infrastructure.ByteArrayWrapper.md) | A picture usually saved in JPEG format. |  [optional]
**specialityCodes** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) | Medical specialty of the healthcare party codified using FHIR or Kmehr codificaiton scheme | 
**notes** | **kotlin.String** | Text notes. |  [optional]
**properties** | [**kotlin.collections.Set&lt;Property&gt;**](Property.md) |  | 
**systemMetaData** | [**SystemMetaDataOwner**](SystemMetaDataOwner.md) |  |  [optional]


<a name="GenderEnum"></a>
## Enum: gender
Name | Value
---- | -----
gender | male, female, indeterminate, changed, changedToMale, changedToFemale, unknown



