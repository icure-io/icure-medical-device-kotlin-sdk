
# Document

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | The Id of the document. We encourage using either a v4 UUID or a HL7 Id. | 
**labels** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  | 
**codes** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) |  | 
**otherUtis** | **kotlin.collections.Set&lt;kotlin.String&gt;** | Extra Uniform Type Identifiers | 
**rev** | **kotlin.String** | The revision of the document in the database, used for conflict management / optimistic locking. |  [optional]
**created** | **kotlin.Long** |  |  [optional]
**modified** | **kotlin.Long** |  |  [optional]
**author** | **kotlin.String** |  |  [optional]
**responsible** | **kotlin.String** |  |  [optional]
**medicalLocationId** | **kotlin.String** |  |  [optional]
**endOfLife** | **kotlin.Long** |  |  [optional]
**deletionDate** | **kotlin.Long** |  |  [optional]
**objectStoreReference** | **kotlin.String** | Reference in object store |  [optional]
**externalUri** | **kotlin.String** | When the document is stored in an external repository, this is the uri of the document in that repository |  [optional]
**mainUti** | **kotlin.String** | The main Uniform Type Identifier of the document (https://developer.apple.com/library/archive/documentation/FileManagement/Conceptual/understanding_utis/understand_utis_conc/understand_utis_conc.html#//apple_ref/doc/uid/TP40001319-CH202-CHDHIJDE) |  [optional]
**name** | **kotlin.String** | Name of the document |  [optional]
**version** | **kotlin.String** | The document version |  [optional]
**externalUuid** | **kotlin.String** | A unique external id (from another external source). |  [optional]
**propertySize** | **kotlin.Long** | Size of the document file |  [optional]
**hash** | **kotlin.String** | Hashed version of the document |  [optional]
**attachmentId** | **kotlin.String** | Id of attachment to this document |  [optional]



