# DeviceApi

All URIs are relative to *http://127.0.0.1:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrModifyMedicalDevice**](DeviceApi.md#createOrModifyMedicalDevice) | **PUT** /rest/v2/medical/device | Create or update a Device
[**createOrModifyMedicalDevices**](DeviceApi.md#createOrModifyMedicalDevices) | **PUT** /rest/v2/medical/device/batch | Create or update a batch of Devices
[**deleteMedicalDevice**](DeviceApi.md#deleteMedicalDevice) | **DELETE** /rest/v2/medical/device/{id} | Delete a Device
[**deleteMedicalDevices**](DeviceApi.md#deleteMedicalDevices) | **POST** /rest/v2/medical/device/batch | Delete Devices
[**filterMedicalDevices**](DeviceApi.md#filterMedicalDevices) | **POST** /rest/v2/medical/device/filter | Find Devices using a filter
[**getMedicalDevice**](DeviceApi.md#getMedicalDevice) | **GET** /rest/v2/medical/device/{id} | Get a Medical Device
[**matchMedicalDevices**](DeviceApi.md#matchMedicalDevices) | **POST** /rest/v2/medical/device/match | Find Devices using a filter


<a name="createOrModifyMedicalDevice"></a>
# **createOrModifyMedicalDevice**
> MedicalDevice createOrModifyMedicalDevice(medicalDevice)

Create or update a Device

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.kraken.client.models.*

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

Create or update a batch of Devices

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.kraken.client.models.*

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
> kotlin.String deleteMedicalDevice(id)

Delete a Device

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.kraken.client.models.*

val apiInstance = DeviceApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteMedicalDevice(id)
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
 **id** | **kotlin.String**|  |

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

Delete Devices

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.kraken.client.models.*

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
> PaginatedListMedicalDevice filterMedicalDevices(filter)

Find Devices using a filter

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.kraken.client.models.*

val apiInstance = DeviceApi()
val filter : Filter =  // Filter | 
try {
    val result : PaginatedListMedicalDevice = apiInstance.filterMedicalDevices(filter)
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
 **filter** | [**Filter**](Filter.md)|  |

### Return type

[**PaginatedListMedicalDevice**](PaginatedListMedicalDevice.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getMedicalDevice"></a>
# **getMedicalDevice**
> MedicalDevice getMedicalDevice(id)

Get a Medical Device

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.kraken.client.models.*

val apiInstance = DeviceApi()
val id : kotlin.String = id_example // kotlin.String | 
try {
    val result : MedicalDevice = apiInstance.getMedicalDevice(id)
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
 **id** | **kotlin.String**|  |

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

Find Devices using a filter

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.kraken.client.models.*

val apiInstance = DeviceApi()
val filter : Filter =  // Filter | 
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
 **filter** | [**Filter**](Filter.md)|  |

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

