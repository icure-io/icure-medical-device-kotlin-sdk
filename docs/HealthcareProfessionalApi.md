# HealthcareProfessionalApi

All URIs are relative to *http://localhost:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyHealthcareProfessional**](HealthcareProfessionalApi.md#createOrModifyHealthcareProfessional) | **PUT** /rest/v2/healthcareprofessional | Create a new healthcare professional or modify an existing one.
[**deleteHealthcareProfessional**](HealthcareProfessionalApi.md#deleteHealthcareProfessional) | **DELETE** /rest/v2/healthcareprofessional/{hcpId} | Delete an existing healthcare professional.
[**filterHealthcareProfessionalBy**](HealthcareProfessionalApi.md#filterHealthcareProfessionalBy) | **POST** /rest/v2/healthcareprofessional/filter | Load healthcare professionals from the database by filtering them using the provided Filter.
[**getHealthcareProfessional**](HealthcareProfessionalApi.md#getHealthcareProfessional) | **GET** /rest/v2/healthcareprofessional/{hcpId} | Get a Healthcare professional by id.
[**matchHealthcareProfessionalBy**](HealthcareProfessionalApi.md#matchHealthcareProfessionalBy) | **POST** /rest/v2/healthcareprofessional/match | Load healthcare professional ids from the database by filtering them using the provided Filter.


<a name="createOrModifyHealthcareProfessional"></a>
# **createOrModifyHealthcareProfessional**
> HealthcareProfessional createOrModifyHealthcareProfessional(healthcareProfessional)

Create a new healthcare professional or modify an existing one.

When modifying an healthcare professional, you must ensure that the rev obtained when getting or creating the healthcare professional is present as the rev is used to guarantee that the healthcare professional has not been modified by a third party.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareProfessionalApi()
val healthcareProfessional : HealthcareProfessional =  // HealthcareProfessional | The healthcare professional that must be created in the database.
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
 **healthcareProfessional** | [**HealthcareProfessional**](HealthcareProfessional.md)| The healthcare professional that must be created in the database. |

### Return type

[**HealthcareProfessional**](HealthcareProfessional.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="deleteHealthcareProfessional"></a>
# **deleteHealthcareProfessional**
> kotlin.String deleteHealthcareProfessional(hcpId)

Delete an existing healthcare professional.

Deletes the healthcare professional identified by the provided unique hcpId.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareProfessionalApi()
val hcpId : kotlin.String = hcpId_example // kotlin.String | The UUID that uniquely identifies the healthcare professional to be deleted.
try {
    val result : kotlin.String = apiInstance.deleteHealthcareProfessional(hcpId)
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
 **hcpId** | **kotlin.String**| The UUID that uniquely identifies the healthcare professional to be deleted. |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="filterHealthcareProfessionalBy"></a>
# **filterHealthcareProfessionalBy**
> PaginatedListHealthcareProfessional filterHealthcareProfessionalBy(filter, nextHcpId, limit)

Load healthcare professionals from the database by filtering them using the provided Filter.

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for Healthcare professionals are AllHealthcareProfessionalsFilter and HealthcarProfessionalsByIdsFilter. This method returns a paginated list of healthcare professionals (with a cursor that lets you query the following items).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareProfessionalApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
val nextHcpId : kotlin.String = nextHcpId_example // kotlin.String | The id of the first Healthcare professional in the next page
val limit : kotlin.Int = 56 // kotlin.Int | The number of healthcare professionals to return in the queried page
try {
    val result : PaginatedListHealthcareProfessional = apiInstance.filterHealthcareProfessionalBy(filter, nextHcpId, limit)
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
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |
 **nextHcpId** | **kotlin.String**| The id of the first Healthcare professional in the next page | [optional]
 **limit** | **kotlin.Int**| The number of healthcare professionals to return in the queried page | [optional]

### Return type

[**PaginatedListHealthcareProfessional**](PaginatedListHealthcareProfessional.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getHealthcareProfessional"></a>
# **getHealthcareProfessional**
> HealthcareProfessional getHealthcareProfessional(hcpId)

Get a Healthcare professional by id.

Each healthcare professional is uniquely identified by a healthcare professional id. The healthcare professional id is a UUID. This hcpId is the preferred method to retrieve one specific healthcare professional.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareProfessionalApi()
val hcpId : kotlin.String = hcpId_example // kotlin.String | The UUID that identifies the healthcare professional uniquely
try {
    val result : HealthcareProfessional = apiInstance.getHealthcareProfessional(hcpId)
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
 **hcpId** | **kotlin.String**| The UUID that identifies the healthcare professional uniquely |

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

Load healthcare professional ids from the database by filtering them using the provided Filter.

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for Healthcare professionals are AllHealthcare professionalsFilter and Healthcare professionalsByIdsFilter. This method returns the list of the ids of the healthcare professionals matching the filter.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = HealthcareProfessionalApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
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
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*
