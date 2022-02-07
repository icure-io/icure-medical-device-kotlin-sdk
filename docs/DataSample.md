
# DataSample

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | The Id of the Data sample. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**transactionId** | **
kotlin.String** | The transactionId is used when a single data sample had to be split into parts for technical reasons. Several data samples with the same non null transaction id form one single data sample |  [optional]
**identifiers** | [**
kotlin.collections.List&lt;Identifier&gt;**](Identifier.md) | Typically used for business / client identifiers. An identifier should identify a data sample uniquely and unambiguously. However, iCure can&#39;t guarantee the uniqueness of those identifiers : This is something you need to take care of. |
**batchId** | **kotlin.String** | Id of the batch that embeds this data sample |  [optional]
**healthElementsIds** | **
kotlin.collections.Set&lt;kotlin.String&gt;** | List of IDs of all healthcare elements for which the data sample is provided. Only used when the Data sample is emitted outside of its batch |  [optional]
**canvasesIds** | **
kotlin.collections.Set&lt;kotlin.String&gt;** | List of Ids of all canvases linked to the Data sample. Only used when the Data sample is emitted outside of its batch. |  [optional]
**index** | **
kotlin.Long** | Used for sorting data samples inside an upper object (A batch, a transaction, a FHIR bundle, ...) |  [optional]
**content** | [**kotlin.collections.Map&lt;kotlin.String,
Content&gt;**](Content.md) | Information contained in the data sample (Measure, number, ...). Content is localized, using ISO language code as key |
**valueDate** | **
kotlin.Long** | The date (YYYYMMDDhhmmss) when the Data sample is noted to have started and also closes on the same date |  [optional]
**openingDate** | **kotlin.Long** | The date (YYYYMMDDhhmmss) of the start of the Data sample |  [optional]
**closingDate** | **kotlin.Long** | The date (YYYYMMDDhhmmss) marking the end of the Data sample |  [optional]
**created** | **
kotlin.Long** | The timestamp (unix epoch in ms) of creation of this data sample in iCure system. Will be filled automatically if not provided. |  [optional]
**modified** | **
kotlin.Long** | The timestamp (unix epoch in ms) of the latest modification of this data sample in iCure system. Will be filled automatically if not provided. |  [optional]
**endOfLife** | **kotlin.Long** | Soft delete (unix epoch in ms) timestamp of the data sample |  [optional]
**author** | **
kotlin.String** | The id of the [User] that created this data sample. When creating the data sample, will be filled automatically by the current user id if not provided. |  [optional]
**responsible** | **
kotlin.String** | The id of the data owner that is responsible of this data sample. When creating the data sample, will be filled automatically by the current user data owner id ([HealthcareProfessional], [Patient] or [MedicalDevice]) if missing |  [optional]
**comment** | **kotlin.String** | Text, comments on the Data sample provided |  [optional]
**qualifiedLinks** | **kotlin.collections.Map&lt;kotlin.String, kotlin.collections.Map&lt;kotlin.String,
kotlin.String&gt;&gt;** | Links towards related data samples (possibly in other batches) |
**codes** | [**
kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) | A code is an item from a codification system that qualifies the content of this data sample. SNOMED-CT, ICPC-2 or ICD-10 codifications systems can be used for codes |
**labels** | [**
kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) | A label is an item from a codification system that qualifies a data sample as being member of a certain class, whatever the value it might have taken. If the label qualifies the content of a field, it means that whatever the content of the field, the label will always apply. LOINC is a codification system typically used for labels. | 



