
# HealthcareElement

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | The Id of the healthcare element. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**identifiers** | [**kotlin.collections.List&lt;Identifier&gt;**](Identifier.md) | Typically used for business / client identifiers. An identifier should identify a data sample uniquely and unambiguously. However, iCure can&#39;t guarantee the uniqueness of those identifiers : This is something you need to take care of. |
**rev** | **kotlin.String** | The revision of the healthcare element in the database, used for conflict management / optimistic locking. |  [optional]
**created** | **kotlin.Long** | The timestamp (unix epoch in ms) of creation of this healthcare element in iCure system. Will be filled automatically if not provided. |  [optional]
**modified** | **kotlin.Long** | The timestamp (unix epoch in ms) of the latest modification of this healthcare element in iCure system. Will be filled automatically if not provided. |  [optional]
**author** | **kotlin.String** | The id of the [User] that created this healthcare element. When creating the healthcare element, will be filled automatically by the current user id if not provided. |  [optional]
**responsible** | **kotlin.String** | The id of the data owner that is responsible of this healthcare element. When creating the healthcare element, will be filled automatically by the current user data owner id ([HealthcareProfessional], [Patient] or [MedicalDevice]) if missing |  [optional]
**medicalLocationId** | **kotlin.String** |  |  [optional]
**tags** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  |
**codes** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) | A code is an item from a codification system that qualifies the content of this healthcare element. SNOMED-CT, ICPC-2 or ICD-10 codifications systems can be used for codes |
**endOfLife** | **kotlin.Long** | Soft delete (unix epoch in ms) timestamp of the healthcare element |  [optional]
**deletionDate** | **kotlin.Long** | the soft delete timestamp. When a healthcare element is ”deleted“, this is set to a non null value: the moment of the deletion |  [optional]
**healthElementId** | **kotlin.String** | The logical id of the healthcare element, used to link together different versions of the same healthcare element. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**valueDate** | **kotlin.Long** | The date (unix epoch in ms) when the healthcare element is noted to have started and also closes on the same date |  [optional]
**openingDate** | **kotlin.Long** | The date (unix epoch in ms) of the start of the healthcare element. |  [optional]
**closingDate** | **kotlin.Long** | The date (unix epoch in ms) marking the end of the healthcare element. |  [optional]
**description** | **kotlin.String** | Description of the healthcare element. |  [optional]
**note** | **kotlin.String** | A text note (can be confidential, encrypted by default). |  [optional]
**systemMetaData** | [**SystemMetaDataEncrypted**](SystemMetaDataEncrypted.md) |  |  [optional]
