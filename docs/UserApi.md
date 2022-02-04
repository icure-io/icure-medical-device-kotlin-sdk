# UserApi

All URIs are relative to *http://127.0.0.1:8912*

Method | HTTP request | Description
------------- | ------------- | -------------
[**checkTokenValidity**](UserApi.md#checkTokenValidity) | **GET** /rest/v2/user/token/{userId} | Check token validity for a user.
[**createOrModifyUser**](UserApi.md#createOrModifyUser) | **PUT** /rest/v2/user | Create a new user or modify an existing one.
[**createToken**](UserApi.md#createToken) | **POST** /rest/v2/user/token/{userId} | Create a token for a user.
[**deleteUser**](UserApi.md#deleteUser) | **DELETE** /rest/v2/user/{userId} | Delete an existing user.
[**filterUsers**](UserApi.md#filterUsers) | **POST** /rest/v2/user/filter | Load users from the database by filtering them using the provided Filter.
[**getLoggedUser**](UserApi.md#getLoggedUser) | **GET** /rest/v2/user | Get the details of the logged User.
[**getUser**](UserApi.md#getUser) | **GET** /rest/v2/user/{userId} | Get a User by id.
[**matchUsers**](UserApi.md#matchUsers) | **POST** /rest/v2/user/match | Load user ids from the database by filtering them using the provided Filter.


<a name="checkTokenValidity"></a>
# **checkTokenValidity**
> kotlin.Boolean checkTokenValidity(userId, token)

Check token validity for a user.

Checks that the provided token is (still) valid for the provided user id (or user login).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val userId : kotlin.String = userId_example // kotlin.String | The UUID that identifies the user uniquely
val token : kotlin.String = token_example // kotlin.String | The token that will be checked
try {
    val result : kotlin.Boolean = apiInstance.checkTokenValidity(userId, token)
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
 **userId** | **kotlin.String**| The UUID that identifies the user uniquely |
 **token** | **kotlin.String**| The token that will be checked |

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

Create a new user or modify an existing one.

A user must have a login, an email or a mobilePhone defined, a user should be linked to either a Healthcare Professional, a Patient or a Device. When modifying an user, you must ensure that the rev obtained when getting or creating the user is present as the rev is used to guarantee that the user has not been modified by a third party.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val user : User =  // User | The user that must be created in the database.
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
 **user** | [**User**](User.md)| The user that must be created in the database. |

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

Create a token for a user.

A token is used to authenticate the user. It is just like a password but it is destined to be used by programs instead of humans. Tokens have a limited validity period (one month).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val userId : kotlin.String = userId_example // kotlin.String | The UUID that identifies the user uniquely
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
 **userId** | **kotlin.String**| The UUID that identifies the user uniquely |

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

Delete an existing user.

Deletes the user identified by the provided unique userId.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val userId : kotlin.String = userId_example // kotlin.String | The UUID that uniquely identifies the user to be deleted.
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
 **userId** | **kotlin.String**| The UUID that uniquely identifies the user to be deleted. |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="filterUsers"></a>
# **filterUsers**
> PaginatedListUser filterUsers(filter, nextUserId, limit)

Load users from the database by filtering them using the provided Filter.

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for Users are AllUsersFilter and UsersByIdsFilter. This method returns a paginated list of users (with a cursor that lets you query the following items).

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
val nextUserId : kotlin.String = nextUserId_example // kotlin.String | The id of the first User in the next page
val limit : kotlin.Int = 56 // kotlin.Int | The number of users to return in the queried page
try {
    val result : PaginatedListUser = apiInstance.filterUsers(filter, nextUserId, limit)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#filterUsers")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#filterUsers")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**Filter**](Filter.md)| The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill |
 **nextUserId** | **kotlin.String**| The id of the first User in the next page | [optional]
 **limit** | **kotlin.Int**| The number of users to return in the queried page | [optional]

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

Get the details of the logged User.

When you make a call to the server, an authentication token is used to identify you. This call returns the complete User object that corresponds to your authentication credentials.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
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

Get a User by id.

Each user is uniquely identified by a user id. The user id is a UUID. This userId is the preferred method to retrieve one specific user.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val userId : kotlin.String = userId_example // kotlin.String | The UUID that identifies the user uniquely
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
 **userId** | **kotlin.String**| The UUID that identifies the user uniquely |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="matchUsers"></a>
# **matchUsers**
> kotlin.collections.List&lt;kotlin.String&gt; matchUsers(filter)

Load user ids from the database by filtering them using the provided Filter.

Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for Users are AllUsersFilter and UsersByIdsFilter. This method returns the list of the ids of the users matching the filter.

### Example
```kotlin
// Import classes:
//import io.icure.kraken.client.infrastructure.*
//import io.icure.md.client.models.*

val apiInstance = UserApi()
val filter : Filter =  // Filter | The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.matchUsers(filter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#matchUsers")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#matchUsers")
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

