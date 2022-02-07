# DataSampleApi

All URIs are relative to *http://localhost:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyDataSampleFor**](DataSampleApi.md#createOrModifyDataSampleFor) | **PUT** /rest/v2/data/sample/for/{patientId} | Create or update a [DataSample] for a patient
[**createOrModifyDataSamplesFor**](DataSampleApi.md#createOrModifyDataSamplesFor) | **PUT** /rest/v2/data/sample/batch/for/{patientId} | Create or update a batch of [DataSample] for a patient
[**deleteAttachment**](DataSampleApi.md#deleteAttachment) | **DELETE** /rest/v2/data/sample/{dataSampleId}/attachment/{documentId} | Delete an attachment of a DataSample
[**deleteDataSample**](DataSampleApi.md#deleteDataSample) | **DELETE** /rest/v2/data/sample/{dataSampleId} | Delete a [DataSample] by its id
[**deleteDataSamples**](DataSampleApi.md#deleteDataSamples) | **POST** /rest/v2/data/sample/batch | Delete a batch of [Data Samples]
[**filterDataSample**](DataSampleApi.md#filterDataSample) | **POST** /rest/v2/data/sample/filter | Find data samples using the provided [filter].
[**getDataSample**](DataSampleApi.md#getDataSample) | **GET** /rest/v2/data/sample/{dataSampleId} | Get a [DataSample] by its id
[**getDataSampleAttachmentContent**](DataSampleApi.md#getDataSampleAttachmentContent) | **GET** /rest/v2/data/sample/{dataSampleId}/attachment/{documentId}/{attachmentId} | Get attachment content of a DataSample
[**getDataSampleAttachmentDocument**](DataSampleApi.md#getDataSampleAttachmentDocument) | **GET** /rest/v2/data/sample/{dataSampleId}/attachment/{documentId} | Get document metadata of a DataSample attachment
[**matchDataSample**](DataSampleApi.md#matchDataSample) | **POST** /rest/v2/data/sample/match | Find data samples ids using the provided Filter.
[**setDataSampleAttachment**](DataSampleApi.md#setDataSampleAttachment) | **PUT** /rest/v2/data/sample/{dataSampleId}/attachment | Add or update the attachment of a DataSample


<a name="createOrModifyDataSampleFor"></a>
# **createOrModifyDataSampleFor**
> DataSample createOrModifyDataSampleFor(patientId, dataSample)

Create or update a [DataSample] for a patient

When modifying a data sample, you can&#39;t update the patient of it : For this, you need to delete the faulty data sample and create a new one. When modifying the data sample, you also need to keep the same batchId : It is not possible to change the batch of a data sample.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val patientId : kotlin.String = patientId_example // kotlin.String | 
val dataSample : DataSample =  // DataSample | 
try {
    val result : DataSample = apiInstance.createOrModifyDataSampleFor(patientId, dataSample)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#createOrModifyDataSampleFor")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#createOrModifyDataSampleFor")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **patientId** | **kotlin.String**|  |
 **dataSample** | [**DataSample**](DataSample.md)|  |

### Return type

[**DataSample**](DataSample.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="createOrModifyDataSamplesFor"></a>
# **createOrModifyDataSamplesFor**
> kotlin.collections.List&lt;DataSample&gt; createOrModifyDataSamplesFor(patientId, dataSample)

Create or update a batch of [DataSample] for a patient

All the provided data samples will be created in the same batch. If you are trying to update some data samples, then those ones need to come from the same batch.                  When modifying a data sample, you can&#39;t update the patient of it : For this, you need to delete the faulty data sample and create a new one. When modifying the data sample, you also need to keep the same batchId : It is not possible to change the batch of a data sample.                 

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val patientId : kotlin.String = patientId_example // kotlin.String | 
val dataSample : kotlin.collections.List<DataSample> =  // kotlin.collections.List<DataSample> | 
try {
    val result : kotlin.collections.List<DataSample> = apiInstance.createOrModifyDataSamplesFor(patientId, dataSample)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#createOrModifyDataSamplesFor")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#createOrModifyDataSamplesFor")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **patientId** | **kotlin.String**|  |
 **dataSample** | [**kotlin.collections.List&lt;DataSample&gt;**](DataSample.md)|  |

### Return type

[**kotlin.collections.List&lt;DataSample&gt;**](DataSample.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="deleteAttachment"></a>
# **deleteAttachment**
> kotlin.String deleteAttachment(dataSampleId, documentId)

Delete an attachment of a DataSample

Deletes an attachment, using its corresponding documentId

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val dataSampleId : kotlin.String = dataSampleId_example // kotlin.String | 
val documentId : kotlin.String = documentId_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteAttachment(dataSampleId, documentId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#deleteAttachment")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#deleteAttachment")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataSampleId** | **kotlin.String**|  |
 **documentId** | **kotlin.String**|  |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="deleteDataSample"></a>
# **deleteDataSample**
> kotlin.String deleteDataSample(dataSampleId)

Delete a [DataSample] by its id

Deletes the data sample identified by the provided unique [dataSampleId].

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val dataSampleId : kotlin.String = dataSampleId_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteDataSample(dataSampleId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#deleteDataSample")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#deleteDataSample")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataSampleId** | **kotlin.String**|  |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="deleteDataSamples"></a>
# **deleteDataSamples**
> kotlin.collections.List&lt;kotlin.String&gt; deleteDataSamples(requestBody)

Delete a batch of [Data Samples]

Deletes the batch of data samples identified by the provided [dataSampleIds]. The data samples to delete need to be part of the same batch

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val requestBody : kotlin.collections.List<kotlin.String> =  // kotlin.collections.List<kotlin.String> | 
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.deleteDataSamples(requestBody)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#deleteDataSamples")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#deleteDataSamples")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestBody** | [**kotlin.collections.List&lt;kotlin.String&gt;**](kotlin.String.md)|  |

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="filterDataSample"></a>
# **filterDataSample**
> PaginatedListDataSample filterDataSample(filter)

Find data samples using the provided [filter].

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for [DataSample] are AllDataSamplesFilter and DataSamplesByIdsFilter. This method returns a paginated list of data samples (with a cursor that lets you query the following items).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
try {
    val result : PaginatedListDataSample = apiInstance.filterDataSample(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#filterDataSample")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#filterDataSample")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |

### Return type

[**PaginatedListDataSample**](PaginatedListDataSample.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getDataSample"></a>
# **getDataSample**
> DataSample getDataSample(dataSampleId)

Get a [DataSample] by its id

Each data sample is uniquely identified by a data sample id which is a UUID. This [dataSampleId] is the preferred method to retrieve one specific data sample.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val dataSampleId : kotlin.String = dataSampleId_example // kotlin.String | 
try {
    val result : DataSample = apiInstance.getDataSample(dataSampleId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#getDataSample")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#getDataSample")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataSampleId** | **kotlin.String**|  |

### Return type

[**DataSample**](DataSample.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getDataSampleAttachmentContent"></a>
# **getDataSampleAttachmentContent**
> kotlinx.coroutines.flow.Flow&lt;java.nio.ByteBuffer&gt; getDataSampleAttachmentContent(dataSampleId, documentId, attachmentId)

Get attachment content of a DataSample

Data Samples may contain attachments such as prescriptions, reports, ... Use this method to get the content of an attachment

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val dataSampleId : kotlin.String = dataSampleId_example // kotlin.String | 
val documentId : kotlin.String = documentId_example // kotlin.String | 
val attachmentId : kotlin.String = attachmentId_example // kotlin.String | 
try {
    val result : kotlinx.coroutines.flow.Flow<java.nio.ByteBuffer> = apiInstance.getDataSampleAttachmentContent(dataSampleId, documentId, attachmentId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#getDataSampleAttachmentContent")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#getDataSampleAttachmentContent")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataSampleId** | **kotlin.String**|  |
 **documentId** | **kotlin.String**|  |
 **attachmentId** | **kotlin.String**|  |

### Return type

[**kotlinx.coroutines.flow.Flow&lt;java.nio.ByteBuffer&gt;**](kotlinx.coroutines.flow.Flow&lt;java.nio.ByteBuffer&gt;.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream

<a name="getDataSampleAttachmentDocument"></a>
# **getDataSampleAttachmentDocument**
> Document getDataSampleAttachmentDocument(dataSampleId, documentId)

Get document metadata of a DataSample attachment

Data Samples may contain attachments such as prescriptions, reports, ... Use this method to get the document metadata information of an attachment

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val dataSampleId : kotlin.String = dataSampleId_example // kotlin.String | 
val documentId : kotlin.String = documentId_example // kotlin.String | 
try {
    val result : Document = apiInstance.getDataSampleAttachmentDocument(dataSampleId, documentId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#getDataSampleAttachmentDocument")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#getDataSampleAttachmentDocument")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataSampleId** | **kotlin.String**|  |
 **documentId** | **kotlin.String**|  |

### Return type

[**Document**](Document.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="matchDataSample"></a>
# **matchDataSample**
> kotlin.collections.List&lt;kotlin.String&gt; matchDataSample(filter)

Find data samples ids using the provided Filter.

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for [DataSample] are AllDataSamplesFilter and DataSamplesByIdsFilter. This method returns a paginated list of data samples (with a cursor that lets you query the following items).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.matchDataSample(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#matchDataSample")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#matchDataSample")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="setDataSampleAttachment"></a>
# **setDataSampleAttachment**
> Document setDataSampleAttachment(dataSampleId, body, documentName, documentVersion, documentExternalUuid, documentLanguage)

Add or update the attachment of a DataSample

Link an attachment or update the attachment of a data sample

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val dataSampleId : kotlin.String = dataSampleId_example // kotlin.String | 
val body : kotlinx.coroutines.flow.Flow<java.nio.ByteBuffer> = BINARY_DATA_HERE // kotlinx.coroutines.flow.Flow<java.nio.ByteBuffer> | 
val documentName : kotlin.String = documentName_example // kotlin.String | 
val documentVersion : kotlin.String = documentVersion_example // kotlin.String | 
val documentExternalUuid : kotlin.String = documentExternalUuid_example // kotlin.String | 
val documentLanguage : kotlin.String = documentLanguage_example // kotlin.String | 
try {
    val result : Document = apiInstance.setDataSampleAttachment(dataSampleId, body, documentName, documentVersion, documentExternalUuid, documentLanguage)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#setDataSampleAttachment")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#setDataSampleAttachment")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataSampleId** | **kotlin.String**|  |
 **body** | **kotlinx.coroutines.flow.Flow&lt;java.nio.ByteBuffer&gt;**|  |
 **documentName** | **kotlin.String**|  | [optional]
 **documentVersion** | **kotlin.String**|  | [optional]
 **documentExternalUuid** | **kotlin.String**|  | [optional]
 **documentLanguage** | **kotlin.String**|  | [optional]

### Return type

[**Document**](Document.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

