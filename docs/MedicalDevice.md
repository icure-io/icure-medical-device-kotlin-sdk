
# MedicalDevice

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | The Id of the MedicalDevice. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**rev** | **kotlin.String** | the revision of the medical device in the database, used for conflict management / optimistic locking. |  [optional]
**deletionDate** | **kotlin.Long** | the soft delete timestamp. When a medical device is ”deleted“, this is set to a non null value: the moment of the deletion |  [optional]
**identifiers** | [**kotlin.collections.List&lt;Identifier&gt;**](Identifier.md) | Typically used for business / client identifiers. An identifier should identify a device uniquely and unambiguously. However, iCure can&#39;t guarantee the uniqueness of those identifiers : This is something you need to take care of. | 
**created** | **kotlin.Long** | the creation date of the medical device (encoded as epoch). |  [optional]
**modified** | **kotlin.Long** | the last modification date of the medical device (encoded as epoch). |  [optional]
**author** | **kotlin.String** | The id of the [User] that created this medical device. When creating the device, this field will be filled automatically by the current user id if not provided. |  [optional]
**responsible** | **kotlin.String** | The id of the data owner that is responsible of this medical device. When creating the medical device, will be filled automatically by the current user data owner id ([HealthcareProfessional], [Patient] or [MedicalDevice]) if missing |  [optional]
**labels** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) | A label is an item from a codification system that qualifies a medical device as being member of a certain class, whatever the value it might have taken. If the label qualifies the content of a field, it means that whatever the content of the field, the label will always apply. LOINC is a codification system typically used for labels. | 
**codes** | [**kotlin.collections.Set&lt;CodingReference&gt;**](CodingReference.md) | A code is an item from a codification system that qualifies the content of this medical device. SNOMED-CT, ICPC-2 or ICD-10 codifications systems can be used for codes | 
**endOfLife** | **kotlin.Long** | Soft delete (unix epoch in ms) timestamp of the medical device |  [optional]
**externalId** | **kotlin.String** | An external (from another source) id with no guarantee or requirement for unicity. |  [optional]
**name** | **kotlin.String** | Name of the device/application recording the data |  [optional]
**type** | **kotlin.String** | Type of device/application recording the data. (eg. \&quot;smartphone\&quot;, \&quot;watch\&quot;,...) |  [optional]
**brand** | **kotlin.String** | Brand of the device recording the data |  [optional]
**model** | **kotlin.String** | Model of the device recording the data |  [optional]
**serialNumber** | **kotlin.String** | Serial number of the device recording the data |  [optional]
**parentId** | **kotlin.String** |  |  [optional]
**picture** | [**kotlin.collections.List&lt;io.icure.kraken.client.infrastructure.ByteArrayWrapper&gt;**](io.icure.kraken.client.infrastructure.ByteArrayWrapper.md) | Picture of the device/application |  [optional]
**properties** | [**kotlin.collections.Set&lt;Property&gt;**](Property.md) |  | 
**systemMetaData** | [**SystemMetaDataOwner**](SystemMetaDataOwner.md) |  |  [optional]



