# DataSampleApi

All URIs are relative to *http://127.0.0.1:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyDataSample**](DataSampleApi.md#createOrModifyDataSample) | **PUT** /rest/v2/data/sample | Create a DataSample
[**createOrModifyDataSamples**](DataSampleApi.md#createOrModifyDataSamples) | **PUT** /rest/v2/data/sample/batch | Create a batch of Data samples
[**deleteAttachment**](DataSampleApi.md#deleteAttachment) | **DELETE** /rest/v2/data/sample/{id}/attachment/{documentId} | Delete a Data sample attachment
[**deleteDataSample**](DataSampleApi.md#deleteDataSample) | **DELETE** /rest/v2/data/sample/{id} | Delete a DataSample
[**deleteDataSamples**](DataSampleApi.md#deleteDataSamples) | **DELETE** /rest/v2/data/sample/batch/{ids} | Delete a batch of Data samples
[**filterDataSample**](DataSampleApi.md#filterDataSample) | **POST** /rest/v2/data/sample/filter | Find Data samples using a filter
[**getDataSample**](DataSampleApi.md#getDataSample) | **GET** /rest/v2/data/sample/{id} | Get a DataSample
[**getDataSampleAttachment**](DataSampleApi.md#getDataSampleAttachment) | **GET** /rest/v2/data/sample/{id}/attachment/{documentId} | Get a DataSample attachment metadata
[**getDataSampleAttachmentContent**](DataSampleApi.md#getDataSampleAttachmentContent) | **GET** /rest/v2/data/sample/{id}/attachment/{documentId}/{attachmentId} | Get a Data sample attachment metadata
[**matchDataSample**](DataSampleApi.md#matchDataSample) | **POST** /rest/v2/data/sample/match | Find Data samples using a filter
[**setDataSampleAttachment**](DataSampleApi.md#setDataSampleAttachment) | **PUT** /rest/v2/data/sample/{id}/attachment/{documentId} | Create a DataSample


<a name="createOrModifyDataSample"></a>
# **createOrModifyDataSample**
> DataSample createOrModifyDataSample(dataSample)

Create a DataSample

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val dataSample : DataSample =  // DataSample | 
try {
    val result : DataSample = apiInstance.createOrModifyDataSample(dataSample)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#createOrModifyDataSample")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#createOrModifyDataSample")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataSample** | [**DataSample**](DataSample.md)|  |

### Return type

[**DataSample**](DataSample.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="createOrModifyDataSamples"></a>
# **createOrModifyDataSamples**
> kotlin.collections.List&lt;DataSample&gt; createOrModifyDataSamples(dataSample)

Create a batch of Data samples

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val dataSample : kotlin.collections.List<DataSample> =  // kotlin.collections.List<DataSample> | 
try {
    val result : kotlin.collections.List<DataSample> = apiInstance.createOrModifyDataSamples(dataSample)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#createOrModifyDataSamples")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#createOrModifyDataSamples")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
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
> kotlin.String deleteAttachment(id, documentId)

Delete a Data sample attachment

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val id : kotlin.String = id_example // kotlin.String | 
val documentId : kotlin.String = documentId_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteAttachment(id, documentId)
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
 **id** | **kotlin.String**|  |
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
> kotlin.String deleteDataSample(id)

Delete a DataSample

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteDataSample(id)
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
 **id** | **kotlin.String**|  |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="deleteDataSamples"></a>
# **deleteDataSamples**
> kotlin.collections.List&lt;kotlin.String&gt; deleteDataSamples(ids)

Delete a batch of Data samples

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val ids : kotlin.collections.List<kotlin.String> =  // kotlin.collections.List<kotlin.String> | 
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.deleteDataSamples(ids)
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
 **ids** | [**kotlin.collections.List&lt;kotlin.String&gt;**](kotlin.String.md)|  | [default to emptyList()]

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="filterDataSample"></a>
# **filterDataSample**
> PaginatedListDataSample filterDataSample(filter)

Find Data samples using a filter

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val filter : Filter =  // Filter | 
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
 **filter** | [**Filter**](Filter.md)|  |

### Return type

[**PaginatedListDataSample**](PaginatedListDataSample.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getDataSample"></a>
# **getDataSample**
> DataSample getDataSample(id)

Get a DataSample

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : DataSample = apiInstance.getDataSample(id)
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
 **id** | **kotlin.String**|  |

### Return type

[**DataSample**](DataSample.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getDataSampleAttachment"></a>
# **getDataSampleAttachment**
> Document getDataSampleAttachment(id, documentId)

Get a DataSample attachment metadata

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val id : kotlin.String = id_example // kotlin.String | 
val documentId : kotlin.String = documentId_example // kotlin.String | 
try {
    val result : Document = apiInstance.getDataSampleAttachment(id, documentId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataSampleApi#getDataSampleAttachment")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataSampleApi#getDataSampleAttachment")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **kotlin.String**|  |
 **documentId** | **kotlin.String**|  |

### Return type

[**Document**](Document.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getDataSampleAttachmentContent"></a>
# **getDataSampleAttachmentContent**
> kotlin.collections.List&lt;kotlin.Any&gt; getDataSampleAttachmentContent(id, documentId, attachmentId)

Get a Data sample attachment metadata

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val id : kotlin.String = id_example // kotlin.String | 
val documentId : kotlin.String = documentId_example // kotlin.String | 
val attachmentId : kotlin.String = attachmentId_example // kotlin.String | 
try {
    val result : kotlin.collections.List<kotlin.Any> = apiInstance.getDataSampleAttachmentContent(id, documentId, attachmentId)
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
 **id** | **kotlin.String**|  |
 **documentId** | **kotlin.String**|  |
 **attachmentId** | **kotlin.String**|  |

### Return type

[**kotlin.collections.List&lt;kotlin.Any&gt;**](kotlin.Any.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="matchDataSample"></a>
# **matchDataSample**
> kotlin.collections.List&lt;kotlin.String&gt; matchDataSample(filter)

Find Data samples using a filter

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val filter : Filter =  // Filter | 
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
 **filter** | [**Filter**](Filter.md)|  |

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="setDataSampleAttachment"></a>
# **setDataSampleAttachment**
> Document setDataSampleAttachment(id, documentId, body)

Create a DataSample

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DataSampleApi()
val id : kotlin.String = id_example // kotlin.String | 
val documentId : kotlin.String = documentId_example // kotlin.String | 
val body : kotlinx.coroutines.flow.Flow<java.nio.ByteBuffer> = BINARY_DATA_HERE // kotlinx.coroutines.flow.Flow<java.nio.ByteBuffer> | 
try {
    val result : Document = apiInstance.setDataSampleAttachment(id, documentId, body)
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
 **id** | **kotlin.String**|  |
 **documentId** | **kotlin.String**|  |
 **body** | **kotlinx.coroutines.flow.Flow&lt;java.nio.ByteBuffer&gt;**|  |

### Return type

[**Document**](Document.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

