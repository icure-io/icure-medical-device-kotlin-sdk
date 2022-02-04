# CodingApi

All URIs are relative to *http://localhost:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyCoding**](CodingApi.md#createOrModifyCoding) | **PUT** /rest/v2/coding | Create or update a [Coding]
[**createOrModifyCodings**](CodingApi.md#createOrModifyCodings) | **PUT** /rest/v2/coding/batch | Create or update a batch of [Coding]
[**deleteCoding**](CodingApi.md#deleteCoding) | **DELETE** /rest/v2/coding/{codingId} | Delete a [Coding]
[**filterCoding**](CodingApi.md#filterCoding) | **POST** /rest/v2/coding/filter | Load codings from the database by filtering them using the provided [filter].
[**getCoding**](CodingApi.md#getCoding) | **GET** /rest/v2/coding/{codingId} | Get a [Coding]
[**matchCoding**](CodingApi.md#matchCoding) | **POST** /rest/v2/coding/match | Load coding ids from the database by filtering them using the provided [filter].


<a name="createOrModifyCoding"></a>
# **createOrModifyCoding**
> Coding createOrModifyCoding(coding)

Create or update a [Coding]

When modifying a coding, you must ensure that the rev obtained when getting or creating the coding is present as the rev is used to guarantee that the coding has not been modified by a third party.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
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

Create or update a batch of [Coding]

When modifying codings, you must ensure that the rev obtained when getting or creating the coding is present as the rev is used to guarantee that the coding has not been modified by a third party.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
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
> kotlin.String deleteCoding(codingId)

Delete a [Coding]

Deletes the coding identified by the provided unique [codingId].

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = CodingApi()
val codingId : kotlin.String = codingId_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteCoding(codingId)
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
 **codingId** | **kotlin.String**|  |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="filterCoding"></a>
# **filterCoding**
> PaginatedListCoding filterCoding(filter, nextCodingId, limit)

Load codings from the database by filtering them using the provided [filter].

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for [Coding] are AllCodingsFilter and CodingsByIdsFilter. This method returns a paginated list of coding (with a cursor that lets you query the following items).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = CodingApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
val nextCodingId : kotlin.String = nextCodingId_example // kotlin.String | The id of the first coding in the next page
val limit : kotlin.Int = 56 // kotlin.Int | The number of codings to return in the queried page
try {
    val result : PaginatedListCoding = apiInstance.filterCoding(filter, nextCodingId, limit)
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
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |
 **nextCodingId** | **kotlin.String**| The id of the first coding in the next page | [optional]
 **limit** | **kotlin.Int**| The number of codings to return in the queried page | [optional]

### Return type

[**PaginatedListCoding**](PaginatedListCoding.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getCoding"></a>
# **getCoding**
> Coding getCoding(codingId)

Get a [Coding]

Each coding is uniquely identified by a coding id. The coding id is a UUID. This [codingId] is the preferred method to retrieve one specific coding.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = CodingApi()
val codingId : kotlin.String = codingId_example // kotlin.String | 
try {
    val result : Coding = apiInstance.getCoding(codingId)
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
 **codingId** | **kotlin.String**|  |

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

Load coding ids from the database by filtering them using the provided [filter].

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for [Coding] are AllCodingsFilter and CodingsByIdsFilter. This method returns a paginated list of coding (with a cursor that lets you query the following items).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = CodingApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
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
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

