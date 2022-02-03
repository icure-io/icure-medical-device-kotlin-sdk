# CodingApi

All URIs are relative to *http://127.0.0.1:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyCoding**](CodingApi.md#createOrModifyCoding) | **PUT** /rest/v2/coding | Create a Coding
[**createOrModifyCodings**](CodingApi.md#createOrModifyCodings) | **PUT** /rest/v2/coding/batch | Create a Coding
[**deleteCoding**](CodingApi.md#deleteCoding) | **DELETE** /rest/v2/coding/{id} | Delete a Coding
[**filterCoding**](CodingApi.md#filterCoding) | **POST** /rest/v2/coding/filter | Find Codings using a filter
[**getCoding**](CodingApi.md#getCoding) | **GET** /rest/v2/coding/{id} | Get a Coding
[**matchCoding**](CodingApi.md#matchCoding) | **POST** /rest/v2/coding/match | Find Codings using a filter


<a name="createOrModifyCoding"></a>
# **createOrModifyCoding**
> Coding createOrModifyCoding(coding)

Create a Coding

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = CodingApi()
val coding : Coding =  // Coding | 
try {
    val result : Coding = apiInstance.createOrModifyCoding(coding)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling CodingApi#createOrModifyCoding")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling CodingApi#createOrModifyCoding")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **coding** | [**Coding**](Coding.md)|  |

### Return type

[**Coding**](Coding.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="createOrModifyCodings"></a>
# **createOrModifyCodings**
> kotlin.collections.List&lt;Coding&gt; createOrModifyCodings(coding)

Create a Coding

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = CodingApi()
val coding : kotlin.collections.List<Coding> =  // kotlin.collections.List<Coding> | 
try {
    val result : kotlin.collections.List<Coding> = apiInstance.createOrModifyCodings(coding)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling CodingApi#createOrModifyCodings")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling CodingApi#createOrModifyCodings")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **coding** | [**kotlin.collections.List&lt;Coding&gt;**](Coding.md)|  |

### Return type

[**kotlin.collections.List&lt;Coding&gt;**](Coding.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="deleteCoding"></a>
# **deleteCoding**
> kotlin.String deleteCoding(id)

Delete a Coding

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = CodingApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteCoding(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling CodingApi#deleteCoding")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling CodingApi#deleteCoding")
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

<a name="filterCoding"></a>
# **filterCoding**
> PaginatedListCoding filterCoding(filter)

Find Codings using a filter

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = CodingApi()
val filter : Filter =  // Filter | 
try {
    val result : PaginatedListCoding = apiInstance.filterCoding(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling CodingApi#filterCoding")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling CodingApi#filterCoding")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**Filter**](Filter.md)|  |

### Return type

[**PaginatedListCoding**](PaginatedListCoding.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getCoding"></a>
# **getCoding**
> Coding getCoding(id)

Get a Coding

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = CodingApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : Coding = apiInstance.getCoding(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling CodingApi#getCoding")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling CodingApi#getCoding")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **kotlin.String**|  |

### Return type

[**Coding**](Coding.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="matchCoding"></a>
# **matchCoding**
> kotlin.collections.List&lt;kotlin.String&gt; matchCoding(filter)

Find Codings using a filter

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = CodingApi()
val filter : Filter =  // Filter | 
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.matchCoding(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling CodingApi#matchCoding")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling CodingApi#matchCoding")
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

