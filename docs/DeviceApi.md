# DeviceApi

All URIs are relative to *http://localhost:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyMedicalDevice**](DeviceApi.md#createOrModifyMedicalDevice) | **PUT** /rest/v2/medical/device | Create or update a [MedicalDevice]
[**createOrModifyMedicalDevices**](DeviceApi.md#createOrModifyMedicalDevices) | **PUT** /rest/v2/medical/device/batch | Create or update a batch of [MedicalDevice]
[**deleteMedicalDevice**](DeviceApi.md#deleteMedicalDevice) | **DELETE** /rest/v2/medical/device/{medicalDeviceId} | Delete a [MedicalDevice]
[**deleteMedicalDevices**](DeviceApi.md#deleteMedicalDevices) | **POST** /rest/v2/medical/device/batch | Delete a batch of [MedicalDevice]
[**filterMedicalDevices**](DeviceApi.md#filterMedicalDevices) | **POST** /rest/v2/medical/device/filter | Load devices from the database by filtering them using the provided [filter].
[**getMedicalDevice**](DeviceApi.md#getMedicalDevice) | **GET** /rest/v2/medical/device/{medicalDeviceId} | Get a Medical Device
[**matchMedicalDevices**](DeviceApi.md#matchMedicalDevices) | **POST** /rest/v2/medical/device/match | Load medical device ids from the database by filtering them using the provided Filter.


<a name="createOrModifyMedicalDevice"></a>
# **createOrModifyMedicalDevice**
> MedicalDevice createOrModifyMedicalDevice(medicalDevice)

Create or update a [MedicalDevice]

When modifying a device, you must ensure that the rev obtained when getting or creating the device is present as the rev is used to guarantee that the device has not been modified by a third party.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DeviceApi()
val medicalDevice : MedicalDevice =  // MedicalDevice | 
try {
    val result : MedicalDevice = apiInstance.createOrModifyMedicalDevice(medicalDevice)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DeviceApi#createOrModifyMedicalDevice")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DeviceApi#createOrModifyMedicalDevice")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **medicalDevice** | [**MedicalDevice**](MedicalDevice.md)|  |

### Return type

[**MedicalDevice**](MedicalDevice.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="createOrModifyMedicalDevices"></a>
# **createOrModifyMedicalDevices**
> kotlin.collections.List&lt;MedicalDevice&gt; createOrModifyMedicalDevices(medicalDevice)

Create or update a batch of [MedicalDevice]

When modifying a device, you must ensure that the rev obtained when getting or creating the device is present as the rev is used to guarantee that the device has not been modified by a third party.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DeviceApi()
val medicalDevice : kotlin.collections.List<MedicalDevice> =  // kotlin.collections.List<MedicalDevice> | 
try {
    val result : kotlin.collections.List<MedicalDevice> = apiInstance.createOrModifyMedicalDevices(medicalDevice)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DeviceApi#createOrModifyMedicalDevices")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DeviceApi#createOrModifyMedicalDevices")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **medicalDevice** | [**kotlin.collections.List&lt;MedicalDevice&gt;**](MedicalDevice.md)|  |

### Return type

[**kotlin.collections.List&lt;MedicalDevice&gt;**](MedicalDevice.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="deleteMedicalDevice"></a>
# **deleteMedicalDevice**
> kotlin.String deleteMedicalDevice(medicalDeviceId)

Delete a [MedicalDevice]

Deletes the medical device identified by the provided unique [medicalDeviceId].

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DeviceApi()
val medicalDeviceId : kotlin.String = medicalDeviceId_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteMedicalDevice(medicalDeviceId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DeviceApi#deleteMedicalDevice")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DeviceApi#deleteMedicalDevice")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **medicalDeviceId** | **kotlin.String**|  |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="deleteMedicalDevices"></a>
# **deleteMedicalDevices**
> kotlin.collections.List&lt;kotlin.String&gt; deleteMedicalDevices(requestBody)

Delete a batch of [MedicalDevice]

Deletes the batch of medical device identified by the provided [medicalDeviceIds].

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DeviceApi()
val requestBody : kotlin.collections.List<kotlin.String> =  // kotlin.collections.List<kotlin.String> | 
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.deleteMedicalDevices(requestBody)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DeviceApi#deleteMedicalDevices")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DeviceApi#deleteMedicalDevices")
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

<a name="filterMedicalDevices"></a>
# **filterMedicalDevices**
> PaginatedListMedicalDevice filterMedicalDevices(filter, nextDeviceId, limit)

Load devices from the database by filtering them using the provided [filter].

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for [MedicalDevice] are AllDevicesFilter and DevicesByIdsFilter. This method returns a paginated list of medical devices (with a cursor that lets you query the following items).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DeviceApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
val nextDeviceId : kotlin.String = nextDeviceId_example // kotlin.String | The id of the first device in the next page
val limit : kotlin.Int = 56 // kotlin.Int | The number of devices to return in the queried page
try {
    val result : PaginatedListMedicalDevice = apiInstance.filterMedicalDevices(filter, nextDeviceId, limit)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DeviceApi#filterMedicalDevices")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DeviceApi#filterMedicalDevices")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |
 **nextDeviceId** | **kotlin.String**| The id of the first device in the next page | [optional]
 **limit** | **kotlin.Int**| The number of devices to return in the queried page | [optional]

### Return type

[**PaginatedListMedicalDevice**](PaginatedListMedicalDevice.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getMedicalDevice"></a>
# **getMedicalDevice**
> MedicalDevice getMedicalDevice(medicalDeviceId)

Get a Medical Device

Each medical device is uniquely identified by a device id. The device id is a UUID. This [medicalDeviceId] is the preferred method to retrieve one specific device.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DeviceApi()
val medicalDeviceId : kotlin.String = medicalDeviceId_example // kotlin.String | 
try {
    val result : MedicalDevice = apiInstance.getMedicalDevice(medicalDeviceId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DeviceApi#getMedicalDevice")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DeviceApi#getMedicalDevice")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **medicalDeviceId** | **kotlin.String**|  |

### Return type

[**MedicalDevice**](MedicalDevice.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="matchMedicalDevices"></a>
# **matchMedicalDevices**
> kotlin.collections.List&lt;kotlin.String&gt; matchMedicalDevices(filter)

Load medical device ids from the database by filtering them using the provided Filter.

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for [MedicalDevice] are AllDevicesFilter and DevicesByIdsFilter. This method returns the list of the ids of the users matching the filter.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = DeviceApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.matchMedicalDevices(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DeviceApi#matchMedicalDevices")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DeviceApi#matchMedicalDevices")
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

