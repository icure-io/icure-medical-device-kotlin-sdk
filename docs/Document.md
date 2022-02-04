
# Document

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | The Id of the document. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**rev** | **kotlin.String** | The revision of the document in the database, used for conflict management / optimistic locking. |  [optional]
**created** | **kotlin.Long** |  |  [optional]
**modified** | **kotlin.Long** |  |  [optional]
**author** | **kotlin.String** |  |  [optional]
**responsible** | **kotlin.String** |  |  [optional]
**medicalLocationId** | **kotlin.String** |  |  [optional]
**deletionDate** | **kotlin.Long** |  |  [optional]
**objectStoreReference** | **kotlin.String** | Reference in object store |  [optional]
**mainUti** | **kotlin.String** | The main Uniform Type Identifier of the document (https://developer.apple.com/library/archive/documentation/FileManagement/Conceptual/understanding_utis/understand_utis_conc/understand_utis_conc.html#//apple_ref/doc/uid/TP40001319-CH202-CHDHIJDE) |  [optional]
**name** | **kotlin.String** | Name of the document |  [optional]
**version** | **kotlin.String** | The document version |  [optional]
**otherUtis** | **kotlin.collections.Set&lt;kotlin.String&gt;** | Extra Uniform Type Identifiers | 
**externalUuid** | **kotlin.String** | A unique external id (from another external source). |  [optional]
**propertySize** | **kotlin.Long** | Size of the document file |  [optional]
**hash** | **kotlin.String** | Hashed version of the document |  [optional]
**attachmentId** | **kotlin.String** | Id of attachment to this document |  [optional]



