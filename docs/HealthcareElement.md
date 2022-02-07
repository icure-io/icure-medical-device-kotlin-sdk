
# HealthcareElement

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | The Id of the healthcare element. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**identifiers** | [**kotlin.collections.List&lt;Identifier&gt;**](Identifier.md) |  | 
**rev** | **kotlin.String** | The revision of the healthcare element in the database, used for conflict management / optimistic locking. |  [optional]
**created** | **kotlin.Long** |  |  [optional]
**modified** | **kotlin.Long** |  |  [optional]
**author** | **kotlin.String** |  |  [optional]
**responsible** | **kotlin.String** |  |  [optional]
**medicalLocationId** | **kotlin.String** |  |  [optional]
**tags** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  | 
**codes** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  | 
**endOfLife** | **kotlin.Long** |  |  [optional]
**deletionDate** | **kotlin.Long** |  |  [optional]
**healthElementId** | **kotlin.String** | The logical id of the healthcare element, used to link together different versions of the same healthcare element. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**valueDate** | **kotlin.Long** | The date (unix epoch in ms) when the healthcare element is noted to have started and also closes on the same date |  [optional]
**openingDate** | **kotlin.Long** | The date (unix epoch in ms) of the start of the healthcare element. |  [optional]
**closingDate** | **kotlin.Long** | The date (unix epoch in ms) marking the end of the healthcare element. |  [optional]
**description** | **kotlin.String** | Description of the healthcare element. |  [optional]
**note** | **kotlin.String** | A text note (can be confidential, encrypted by default). |  [optional]
**systemMetaData** | [**SystemMetaDataEncrypted**](SystemMetaDataEncrypted.md) |  |  [optional]



