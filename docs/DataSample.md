
# DataSample

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | The Id of the Data sample. We encourage using either a v4 UUID or a HL7 Id. | 
**identifier** | [**kotlin.collections.List&lt;Identifier&gt;**](Identifier.md) |  | 
**content** | [**kotlin.collections.Map&lt;kotlin.String, Content&gt;**](Content.md) | Information contained in the data sample. Content is localized, using ISO language code as key | 
**qualifiedLinks** | **kotlin.collections.Map&lt;kotlin.String, kotlin.collections.Map&lt;kotlin.String, kotlin.String&gt;&gt;** | Links towards related data samples (possibly in other contacts) | 
**codes** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  | 
**labels** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  | 
**transactionId** | **kotlin.String** | The transactionId is used when a single data sample had to be split into parts for technical reasons. Several data samples with the same non null transaction id form one single data sample |  [optional]
**batchId** | **kotlin.String** | Id of the batch that embeds this data sample |  [optional]
**healthElementsIds** | **kotlin.collections.Set&lt;kotlin.String&gt;** | List of IDs of all healthcare elements for which the data sample is provided. Only used when the Data sample is emitted outside of its contact |  [optional]
**canvasesIds** | **kotlin.collections.Set&lt;kotlin.String&gt;** | List of Ids of all canvases linked to the Data sample. Only used when the Data sample is emitted outside of its contact. |  [optional]
**index** | **kotlin.Long** | Used for sorting data samples inside an upper object (A contact, a transaction, a FHIR bundle, ...) |  [optional]
**valueDate** | **kotlin.Long** | The date (YYYYMMDDhhmmss) when the Data sample is noted to have started and also closes on the same date |  [optional]
**openingDate** | **kotlin.Long** | The date (YYYYMMDDhhmmss) of the start of the Data sample |  [optional]
**closingDate** | **kotlin.Long** | The date (YYYYMMDDhhmmss) marking the end of the Data sample |  [optional]
**created** | **kotlin.Long** |  |  [optional]
**modified** | **kotlin.Long** |  |  [optional]
**endOfLife** | **kotlin.Long** |  |  [optional]
**author** | **kotlin.String** |  |  [optional]
**responsible** | **kotlin.String** |  |  [optional]
**comment** | **kotlin.String** | Text, comments on the Data sample provided |  [optional]



