# HealthcareElementApi

All URIs are relative to *http://localhost:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyHealthcareElement**](HealthcareElementApi.md#createOrModifyHealthcareElement) | **PUT** /rest/v2/hce/{patientId} | Create a [HealthcareElement]
[**createOrModifyHealthcareElements**](HealthcareElementApi.md#createOrModifyHealthcareElements) | **PUT** /rest/v2/hce/batch/{patientId} | Create a batch of [HealthcareElement]
[**deleteHealthcareElement**](HealthcareElementApi.md#deleteHealthcareElement) | **DELETE** /rest/v2/hce/{healthElementId} | Delete a Healthcare Element
[**filterHealthcareElement**](HealthcareElementApi.md#filterHealthcareElement) | **POST** /rest/v2/hce/filter | Load healthcare elements from the database by filtering them using the provided [filter].
[**getHealthcareElement**](HealthcareElementApi.md#getHealthcareElement) | **GET** /rest/v2/hce/{healthcareElementId} | Get a [HealthcareElement]
[**matchHealthcareElement**](HealthcareElementApi.md#matchHealthcareElement) | **POST** /rest/v2/hce/match | Load healthcare elements ids from the database by filtering them using the provided [filter].


<a name="createOrModifyHealthcareElement"></a>
# **createOrModifyHealthcareElement**
> HealthcareElement createOrModifyHealthcareElement(patientId, healthcareElement)

Create a [HealthcareElement]

When modifying an healthcare element, you must ensure that the rev obtained when getting or creating the healthcare element is present as the rev is used to guarantee that the healthcare element has not been modified by a third party.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val patientId : kotlin.String = patientId_example // kotlin.String | 
val healthcareElement : HealthcareElement =  // HealthcareElement | The healthcare element that must be created in the database.
try {
    val result : HealthcareElement = apiInstance.createOrModifyHealthcareElement(patientId, healthcareElement)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareElementApi#createOrModifyHealthcareElement")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareElementApi#createOrModifyHealthcareElement")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **patientId** | **kotlin.String**|  |
 **healthcareElement** | [**HealthcareElement**](HealthcareElement.md)| The healthcare element that must be created in the database. |

### Return type

[**HealthcareElement**](HealthcareElement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="createOrModifyHealthcareElements"></a>
# **createOrModifyHealthcareElements**
> kotlin.collections.List&lt;HealthcareElement&gt; createOrModifyHealthcareElements(patientId, healthcareElement)

Create a batch of [HealthcareElement]

When modifying an healthcare element, you must ensure that the rev obtained when getting or creating the healthcare element is present as the rev is used to guarantee that the healthcare element has not been modified by a third party.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val patientId : kotlin.String = patientId_example // kotlin.String | 
val healthcareElement : kotlin.collections.List<HealthcareElement> =  // kotlin.collections.List<HealthcareElement> | The healthcare element that must be created in the database.
try {
    val result : kotlin.collections.List<HealthcareElement> = apiInstance.createOrModifyHealthcareElements(patientId, healthcareElement)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareElementApi#createOrModifyHealthcareElements")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareElementApi#createOrModifyHealthcareElements")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **patientId** | **kotlin.String**|  |
 **healthcareElement** | [**kotlin.collections.List&lt;HealthcareElement&gt;**](HealthcareElement.md)| The healthcare element that must be created in the database. |

### Return type

[**kotlin.collections.List&lt;HealthcareElement&gt;**](HealthcareElement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="deleteHealthcareElement"></a>
# **deleteHealthcareElement**
> kotlin.String deleteHealthcareElement(healthElementId)

Delete a Healthcare Element

Deletes the healthcare element identified by the provided unique [healthElementId].

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val healthElementId : kotlin.String = healthElementId_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteHealthcareElement(healthElementId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareElementApi#deleteHealthcareElement")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareElementApi#deleteHealthcareElement")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **healthElementId** | **kotlin.String**|  |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="filterHealthcareElement"></a>
# **filterHealthcareElement**
> PaginatedListHealthcareElement filterHealthcareElement(filter, nextHealthElementId, limit)

Load healthcare elements from the database by filtering them using the provided [filter].

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for Healthcare element are AllHealthcareElementsFilter and HealthcareElementsByIdsFilter. This method returns a paginated list of healthcare element (with a cursor that lets you query the following items).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
val nextHealthElementId : kotlin.String = nextHealthElementId_example // kotlin.String | The id of the first [HealthcareElement] in the next page
val limit : kotlin.Int = 56 // kotlin.Int | The number of healthcare elements to return in the queried page
try {
    val result : PaginatedListHealthcareElement = apiInstance.filterHealthcareElement(filter, nextHealthElementId, limit)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareElementApi#filterHealthcareElement")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareElementApi#filterHealthcareElement")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |
 **nextHealthElementId** | **kotlin.String**| The id of the first [HealthcareElement] in the next page | [optional]
 **limit** | **kotlin.Int**| The number of healthcare elements to return in the queried page | [optional]

### Return type

[**PaginatedListHealthcareElement**](PaginatedListHealthcareElement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getHealthcareElement"></a>
# **getHealthcareElement**
> HealthcareElement getHealthcareElement(healthcareElementId)

Get a [HealthcareElement]

Each [HealthcareElement] is uniquely identified by a healthcare element id. The healthcare element id is a UUID. This [healthcareElementId] is the preferred method to retrieve one specific healthcare element.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val healthcareElementId : kotlin.String = healthcareElementId_example // kotlin.String | 
try {
    val result : HealthcareElement = apiInstance.getHealthcareElement(healthcareElementId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareElementApi#getHealthcareElement")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareElementApi#getHealthcareElement")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **healthcareElementId** | **kotlin.String**|  |

### Return type

[**HealthcareElement**](HealthcareElement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="matchHealthcareElement"></a>
# **matchHealthcareElement**
> kotlin.collections.List&lt;kotlin.String&gt; matchHealthcareElement(filter)

Load healthcare elements ids from the database by filtering them using the provided [filter].

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for Healthcare element are AllHealthcareElementsFilter and HealthcareElementsByIdsFilter. This method returns a paginated list of healthcare element (with a cursor that lets you query the following items).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.matchHealthcareElement(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareElementApi#matchHealthcareElement")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareElementApi#matchHealthcareElement")
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

