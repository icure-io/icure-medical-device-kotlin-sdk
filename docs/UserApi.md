# UserApi

All URIs are relative to *http://127.0.0.1:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**checkTokenValidity**](UserApi.md#checkTokenValidity) | **GET** /rest/v2/user/token/{userId} | Find Users using a filter
[**createOrModifyUser**](UserApi.md#createOrModifyUser) | **PUT** /rest/v2/user | Create a User
[**createToken**](UserApi.md#createToken) | **POST** /rest/v2/user/token/{userId} | Find Users using a filter
[**deleteUser**](UserApi.md#deleteUser) | **DELETE** /rest/v2/user/{userId} | Delete a User
[**filterUser**](UserApi.md#filterUser) | **POST** /rest/v2/user/filter | Find Users using a filter
[**getLoggedUser**](UserApi.md#getLoggedUser) | **GET** /rest/v2/user | Get the logged User
[**getUser**](UserApi.md#getUser) | **GET** /rest/v2/user/{userId} | Get a User
[**matchUser**](UserApi.md#matchUser) | **POST** /rest/v2/user/match | Find Users using a filter


<a name="checkTokenValidity"></a>
# **checkTokenValidity**
> kotlin.Boolean checkTokenValidity(xIcureToken, userId)

Find Users using a filter

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val xIcureToken : kotlin.String = xIcureToken_example // kotlin.String | 
val userId : kotlin.String = userId_example // kotlin.String | 
try {
    val result : kotlin.Boolean = apiInstance.checkTokenValidity(xIcureToken, userId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#checkTokenValidity")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#checkTokenValidity")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xIcureToken** | **kotlin.String**|  |
 **userId** | **kotlin.String**|  |

### Return type

**kotlin.Boolean**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="createOrModifyUser"></a>
# **createOrModifyUser**
> User createOrModifyUser(user)

Create a User

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val user : User =  // User | 
try {
    val result : User = apiInstance.createOrModifyUser(user)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#createOrModifyUser")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#createOrModifyUser")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user** | [**User**](User.md)|  |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="createToken"></a>
# **createToken**
> kotlin.String createToken(userId)

Find Users using a filter

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val userId : kotlin.String = userId_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.createToken(userId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#createToken")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#createToken")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **kotlin.String**|  |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="deleteUser"></a>
# **deleteUser**
> kotlin.String deleteUser(userId)

Delete a User

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val userId : kotlin.String = userId_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.deleteUser(userId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#deleteUser")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#deleteUser")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **kotlin.String**|  |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="filterUser"></a>
# **filterUser**
> PaginatedListUser filterUser(filter)

Find Users using a filter

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val filter : Filter =  // Filter | 
try {
    val result : PaginatedListUser = apiInstance.filterUser(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#filterUser")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#filterUser")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**Filter**](Filter.md)|  |

### Return type

[**PaginatedListUser**](PaginatedListUser.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

<a name="getLoggedUser"></a>
# **getLoggedUser**
> User getLoggedUser()

Get the logged User

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
try {
    val result : User = apiInstance.getLoggedUser()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#getLoggedUser")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#getLoggedUser")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getUser"></a>
# **getUser**
> User getUser(userId)

Get a User

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val userId : kotlin.String = userId_example // kotlin.String | 
try {
    val result : User = apiInstance.getUser(userId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#getUser")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#getUser")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **kotlin.String**|  |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="matchUser"></a>
# **matchUser**
> kotlin.collections.List&lt;kotlin.String&gt; matchUser(filter)

Find Users using a filter

### Example
```kotlin
// Import classes:
//import io.icure.md.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val filter : Filter =  // Filter | 
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.matchUser(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#matchUser")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#matchUser")
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

