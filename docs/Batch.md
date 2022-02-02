
# Batch

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | the Id of the batch. We encourage using either a v4 UUID or a HL7 Id. | 
**labels** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  | 
**codes** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  | 
**services** | [**kotlin.collections.Set&lt;DataSample&gt;**](DataSample.md) | Set of all services provided to the patient during the batch. | 
**rev** | **kotlin.String** | the revision of the batch in the database, used for conflict management / optimistic locking. |  [optional]
**created** | **kotlin.Long** |  |  [optional]
**modified** | **kotlin.Long** |  |  [optional]
**author** | **kotlin.String** |  |  [optional]
**responsible** | **kotlin.String** |  |  [optional]
**endOfLife** | **kotlin.Long** |  |  [optional]
**deletionDate** | **kotlin.Long** |  |  [optional]
**groupId** | **kotlin.String** | Separate batches can merged in one logical batch if they share the same groupId. When a batch must be split to selectively assign rights to healthcare parties, the split batches all share the same groupId |  [optional]
**openingDate** | **kotlin.Long** | The date (YYYYMMDDhhmmss) of the start of the batch. |  [optional]
**closingDate** | **kotlin.Long** | The date (YYYYMMDDhhmmss) marking the end of the batch. |  [optional]
**description** | **kotlin.String** | Description of the batch |  [optional]
**location** | **kotlin.String** | Location where the batch was recorded. |  [optional]



