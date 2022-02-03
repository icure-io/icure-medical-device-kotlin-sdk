# HealthcareProfessionalApi

All URIs are relative to *http://127.0.0.1:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyHealthcareProfessional**](HealthcareProfessionalApi.md#createOrModifyHealthcareProfessional) | **PUT** /rest/v2/healthcareprofessional | Create a HealthcareProfessional
[**deleteHealthcareProfessional**](HealthcareProfessionalApi.md#deleteHealthcareProfessional) | **DELETE** /rest/v2/healthcareprofessional/{id} | Delete a HealthcareProfessional
[**filterHealthcareProfessionalBy**](HealthcareProfessionalApi.md#filterHealthcareProfessionalBy) | **POST** /rest/v2/healthcareprofessional/filter | Find Healthcare Professional using a filter
[**getHealthcareProfessional**](HealthcareProfessionalApi.md#getHealthcareProfessional) | **GET** /rest/v2/healthcareprofessional/{id} | Get a HealthcareProfessional
[**matchHealthcareProfessionalBy**](HealthcareProfessionalApi.md#matchHealthcareProfessionalBy) | **POST** /rest/v2/healthcareprofessional/match | Find Data samples using a filter


<a name="createOrModifyHealthcareProfessional"></a>
# **createOrModifyHealthcareProfessional**
> HealthcareProfessional createOrModifyHealthcareProfessional(healthcareProfessional)

Create a HealthcareProfessional

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareProfessionalApi()
val healthcareProfessional : HealthcareProfessional =  // HealthcareProfessional | 
try {
    val result : HealthcareProfessional = apiInstance.createOrModifyHealthcareProfessional(healthcareProfessional)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareProfessionalApi#createOrModifyHealthcareProfessional")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareProfessionalApi#createOrModifyHealthcareProfessional")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **healthcareProfessional** | [**HealthcareProfessional**](HealthcareProfessional.md)|  |

### Return type

[**HealthcareProfessional**](HealthcareProfessional.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="deleteHealthcareProfessional"></a>
# **deleteHealthcareProfessional**
> kotlin.String deleteHealthcareProfessional(id)

Delete a HealthcareProfessional

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareProfessionalApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteHealthcareProfessional(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareProfessionalApi#deleteHealthcareProfessional")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareProfessionalApi#deleteHealthcareProfessional")
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

<a name="filterHealthcareProfessionalBy"></a>
# **filterHealthcareProfessionalBy**
> PaginatedListHealthcareProfessional filterHealthcareProfessionalBy(filter)

Find Healthcare Professional using a filter

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareProfessionalApi()
val filter : Filter =  // Filter | 
try {
    val result : PaginatedListHealthcareProfessional = apiInstance.filterHealthcareProfessionalBy(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareProfessionalApi#filterHealthcareProfessionalBy")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareProfessionalApi#filterHealthcareProfessionalBy")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**Filter**](Filter.md)|  |

### Return type

[**PaginatedListHealthcareProfessional**](PaginatedListHealthcareProfessional.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getHealthcareProfessional"></a>
# **getHealthcareProfessional**
> HealthcareProfessional getHealthcareProfessional(id)

Get a HealthcareProfessional

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareProfessionalApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : HealthcareProfessional = apiInstance.getHealthcareProfessional(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareProfessionalApi#getHealthcareProfessional")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareProfessionalApi#getHealthcareProfessional")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **kotlin.String**|  |

### Return type

[**HealthcareProfessional**](HealthcareProfessional.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="matchHealthcareProfessionalBy"></a>
# **matchHealthcareProfessionalBy**
> kotlin.collections.List&lt;kotlin.String&gt; matchHealthcareProfessionalBy(filter)

Find Data samples using a filter

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareProfessionalApi()
val filter : Filter =  // Filter | 
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.matchHealthcareProfessionalBy(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling HealthcareProfessionalApi#matchHealthcareProfessionalBy")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling HealthcareProfessionalApi#matchHealthcareProfessionalBy")
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

