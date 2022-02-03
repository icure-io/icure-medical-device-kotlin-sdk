# PatientApi

All URIs are relative to *http://127.0.0.1:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyPatient**](PatientApi.md#createOrModifyPatient) | **PUT** /rest/v2/patient | Create or update a Patient
[**deletePatient**](PatientApi.md#deletePatient) | **DELETE** /rest/v2/patient/{id} | Delete a Patient
[**filterPatients**](PatientApi.md#filterPatients) | **POST** /rest/v2/patient/filter | Find Patients using a filter
[**getPatient**](PatientApi.md#getPatient) | **GET** /rest/v2/patient/{id} | Get a Patient
[**matchPatients**](PatientApi.md#matchPatients) | **POST** /rest/v2/patient/match | Find Patients using a filter


<a name="createOrModifyPatient"></a>
# **createOrModifyPatient**
> Patient createOrModifyPatient(patient)

Create or update a Patient

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = PatientApi()
val patient : Patient =  // Patient | 
try {
    val result : Patient = apiInstance.createOrModifyPatient(patient)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PatientApi#createOrModifyPatient")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PatientApi#createOrModifyPatient")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **patient** | [**Patient**](Patient.md)|  |

### Return type

[**Patient**](Patient.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="deletePatient"></a>
# **deletePatient**
> kotlin.String deletePatient(id)

Delete a Patient

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = PatientApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deletePatient(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PatientApi#deletePatient")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PatientApi#deletePatient")
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

<a name="filterPatients"></a>
# **filterPatients**
> PaginatedListPatient filterPatients(filter)

Find Patients using a filter

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = PatientApi()
val filter : Filter =  // Filter | 
try {
    val result : PaginatedListPatient = apiInstance.filterPatients(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PatientApi#filterPatients")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PatientApi#filterPatients")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**Filter**](Filter.md)|  |

### Return type

[**PaginatedListPatient**](PaginatedListPatient.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getPatient"></a>
# **getPatient**
> Patient getPatient(id)

Get a Patient

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = PatientApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : Patient = apiInstance.getPatient(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PatientApi#getPatient")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PatientApi#getPatient")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **kotlin.String**|  |

### Return type

[**Patient**](Patient.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="matchPatients"></a>
# **matchPatients**
> kotlin.collections.List&lt;kotlin.String&gt; matchPatients(filter)

Find Patients using a filter

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = PatientApi()
val filter : Filter =  // Filter | 
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.matchPatients(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PatientApi#matchPatients")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PatientApi#matchPatients")
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

