# HealthcareElementApi

All URIs are relative to *http://127.0.0.1:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyHealthcareElement**](HealthcareElementApi.md#createOrModifyHealthcareElement) | **PUT** /rest/v2/hce | Create a Healthcare Element
[**createOrModifyHealthcareElements**](HealthcareElementApi.md#createOrModifyHealthcareElements) | **PUT** /rest/v2/hce/batch | Create a Healthcare Element
[**deleteHealthcareElement**](HealthcareElementApi.md#deleteHealthcareElement) | **DELETE** /rest/v2/hce/{id} | Delete a Healthcare Element
[**filterHealthcareElement**](HealthcareElementApi.md#filterHealthcareElement) | **POST** /rest/v2/hce/filter | Find Healthcare Elements using a filter
[**getHealthcareElement**](HealthcareElementApi.md#getHealthcareElement) | **GET** /rest/v2/hce/{id} | Get a Healthcare Element
[**matchHealthcareElement**](HealthcareElementApi.md#matchHealthcareElement) | **POST** /rest/v2/hce/match | Find Healthcare Elements using a filter


<a name="createOrModifyHealthcareElement"></a>
# **createOrModifyHealthcareElement**
> HealthcareElement createOrModifyHealthcareElement(healthcareElement)

Create a Healthcare Element

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val healthcareElement : HealthcareElement =  // HealthcareElement | 
try {
    val result : HealthcareElement = apiInstance.createOrModifyHealthcareElement(healthcareElement)
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
 **healthcareElement** | [**HealthcareElement**](HealthcareElement.md)|  |

### Return type

[**HealthcareElement**](HealthcareElement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="createOrModifyHealthcareElements"></a>
# **createOrModifyHealthcareElements**
> kotlin.collections.List&lt;HealthcareElement&gt; createOrModifyHealthcareElements(healthcareElement)

Create a Healthcare Element

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val healthcareElement : kotlin.collections.List<HealthcareElement> =  // kotlin.collections.List<HealthcareElement> | 
try {
    val result : kotlin.collections.List<HealthcareElement> = apiInstance.createOrModifyHealthcareElements(healthcareElement)
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
 **healthcareElement** | [**kotlin.collections.List&lt;HealthcareElement&gt;**](HealthcareElement.md)|  |

### Return type

[**kotlin.collections.List&lt;HealthcareElement&gt;**](HealthcareElement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="deleteHealthcareElement"></a>
# **deleteHealthcareElement**
> kotlin.String deleteHealthcareElement(id)

Delete a Healthcare Element

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteHealthcareElement(id)
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
 **id** | **kotlin.String**|  |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="filterHealthcareElement"></a>
# **filterHealthcareElement**
> PaginatedListHealthcareElement filterHealthcareElement(filter)

Find Healthcare Elements using a filter

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val filter : Filter =  // Filter | 
try {
    val result : PaginatedListHealthcareElement = apiInstance.filterHealthcareElement(filter)
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
 **filter** | [**Filter**](Filter.md)|  |

### Return type

[**PaginatedListHealthcareElement**](PaginatedListHealthcareElement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getHealthcareElement"></a>
# **getHealthcareElement**
> HealthcareElement getHealthcareElement(id)

Get a Healthcare Element

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : HealthcareElement = apiInstance.getHealthcareElement(id)
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
 **id** | **kotlin.String**|  |

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

Find Healthcare Elements using a filter

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareElementApi()
val filter : Filter =  // Filter | 
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
 **filter** | [**Filter**](Filter.md)|  |

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

