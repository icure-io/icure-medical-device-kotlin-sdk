# PatientApi

All URIs are relative to *http://127.0.0.1:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyPatient**](PatientApi.md#createOrModifyPatient) | **PUT** /rest/v2/patient | Create or update a [Patient]
[**deletePatient**](PatientApi.md#deletePatient) | **DELETE** /rest/v2/patient/{patientId} | Delete a [Patient]
[**filterPatients**](PatientApi.md#filterPatients) | **POST** /rest/v2/patient/filter | Load patients from the database by filtering them using the provided [filter].
[**getPatient**](PatientApi.md#getPatient) | **GET** /rest/v2/patient/{patientId} | Get a [Patient]
[**matchPatients**](PatientApi.md#matchPatients) | **POST** /rest/v2/patient/match | Load patient ids from the database by filtering them using the provided [filter].


<a name="createOrModifyPatient"></a>
# **createOrModifyPatient**
> Patient createOrModifyPatient(patient)

Create or update a [Patient]

When modifying a patient, you must ensure that the rev obtained when getting or creating the patient is present as the rev is used to guarantee that the patient has not been modified by a third party.

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
> kotlin.String deletePatient(patientId)

Delete a [Patient]

Deletes the patient identified by the provided unique [patientId].

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = PatientApi()
val patientId : kotlin.String = patientId_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deletePatient(patientId)
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
 **patientId** | **kotlin.String**|  |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="filterPatients"></a>
# **filterPatients**
> PaginatedListPatient filterPatients(filter, nextPatientId, limit)

Load patients from the database by filtering them using the provided [filter].

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for [Patient] are AllPatientsFilter and PatientsByIdsFilter. This method returns a paginated list of patient (with a cursor that lets you query the following items).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = PatientApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
val nextPatientId : kotlin.String = nextPatientId_example // kotlin.String | The id of the first patient in the next page
val limit : kotlin.Int = 56 // kotlin.Int | The number of patients to return in the queried page
try {
    val result : PaginatedListPatient = apiInstance.filterPatients(filter, nextPatientId, limit)
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
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |
 **nextPatientId** | **kotlin.String**| The id of the first patient in the next page | [optional]
 **limit** | **kotlin.Int**| The number of patients to return in the queried page | [optional]

### Return type

[**PaginatedListPatient**](PaginatedListPatient.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getPatient"></a>
# **getPatient**
> Patient getPatient(patientId)

Get a [Patient]

Each patient is uniquely identified by a patient id. The patient id is a UUID. This [patientId] is the preferred method to retrieve one specific patient.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = PatientApi()
val patientId : kotlin.String = patientId_example // kotlin.String | 
try {
    val result : Patient = apiInstance.getPatient(patientId)
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
 **patientId** | **kotlin.String**|  |

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

Load patient ids from the database by filtering them using the provided [filter].

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for [Patient] are AllPatientsFilter and PatientsByIdsFilter. This method returns the list of the ids of the users matching the [filter].

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = PatientApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
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
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

