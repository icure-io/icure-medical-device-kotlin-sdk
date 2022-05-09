/**
 * ICure Medical Device Micro Service
 *
 * ICure Medical Device Micro Service
 *
 * The version of the OpenAPI document: v2
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */
package io.icure.md.client.apis

import io.icure.kraken.client.infrastructure.ClientException
import io.icure.kraken.client.infrastructure.ServerException
import io.icure.md.client.filter.Filter
import io.icure.md.client.models.PaginatedListUser
import io.icure.md.client.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Named
import kotlin.time.ExperimentalTime

@Named
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
interface UserApi {

    /**
    * Check token validity for a user.
    * Checks that the provided token is (still) valid for the provided user id (or user login).
    * @param userId The UUID that identifies the user uniquely 
    * @param token The token that will be checked 
    * @return Returns a boolean (true/false). True if the token is valid, False otherwise
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws ClientException if there is no user with the provided userId.
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun checkTokenValidity(userId: kotlin.String, token: kotlin.String) : kotlin.Boolean 

    /**
    * Create a new user or modify an existing one.
    * A user must have a login, an email or a mobilePhone defined, a user should be linked to either a Healthcare Professional, a Patient or a Device. When modifying an user, you must ensure that the rev obtained when getting or creating the user is present as the rev is used to guarantee that the user has not been modified by a third party.
    * @param user The user that must be created in the database. 
    * @return Returns the created or modified user as a User object, with an updated rev.
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws ClientException if there is no login,email or mobilePhone in the provided User
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun createOrModifyUser(user: User) : User 

    /**
    * Create a token for a user.
     * A token is used to authenticate the user. It is just like a password but it is destined to be used by programs instead of humans. Tokens have a limited validity period (one month).
     * @param userId The UUID that identifies the user uniquely
     * @return Returns the token that can be subsequently used to authenticate the user with id userId.
     * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
     * @throws ClientException if there is no user with the provided userId.
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun createToken(userId: kotlin.String): kotlin.String

    /**
     * Create a token for a user.
     * A token is used to authenticate the user. It is just like a password but it is destined to be used by programs instead of humans. Tokens have a limited validity period (one month).
     * @param userId The UUID that identifies the user uniquely
     * @param validityInSeconds The validity of the token in seconds unit
     * @return Returns the token that can be subsequently used to authenticate the user with id userId.
     * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
     * @throws ClientException if there is no user with the provided userId.
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ServerException If the API returns a server error response
     */
    @OptIn(ExperimentalTime::class)
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun createToken(userId: String, validityInSeconds: Long): String?

    /**
     * Delete an existing user.
     * Deletes the user identified by the provided unique userId.
     * @param userId The UUID that uniquely identifies the user to be deleted.
     * @return Returns the rev of the deleted object.
     * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
     * @throws ClientException if there is no user with the provided userId.
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun deleteUser(userId: kotlin.String) : kotlin.String 

    /**
    * Load users from the database by filtering them using the provided Filter.
    * Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for Users are AllUsersFilter and UsersByIdsFilter. This method returns a paginated list of users (with a cursor that lets you query the following items).
    * @param filter The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill 
    * @param nextUserId The id of the first User in the next page (optional)
    * @param limit The number of users to return in the queried page (optional)
    * @return Returns a PaginatedList of Users.
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws ClientException if there is no user with the provided userId.
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun filterUsers(filter: Filter<User>, nextUserId: kotlin.String?, limit: kotlin.Int?) : PaginatedListUser 

    /**
    * Get the details of the logged User.
    * When you make a call to the server, an authentication token is used to identify you. This call returns the complete User object that corresponds to your authentication credentials.
    * @return Returns the logged user in the body
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun getLoggedUser() : User 

    /**
    * Get a User by id.
     * Each user is uniquely identified by a user id. The user id is a UUID. This userId is the preferred method to retrieve one specific user.
     * @param userId The UUID that identifies the user uniquely
     * @return Returns the fetched user as a User object
     * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
     * @throws ClientException if there is no user with the provided userId.
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun getUser(userId: kotlin.String): User

    /**
     * Get a User by its email.
     * Each user is uniquely identified by a user email.
     * @param email The email that identifies the user
     * @return Returns the fetched user as a User object
     * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
     * @throws ClientException if there is no user with the provided userId.
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun getUserByEmail(email: kotlin.String): User

    /**
     * Load user ids from the database by filtering them using the provided Filter.
     * Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for Users are AllUsersFilter and UsersByIdsFilter. This method returns the list of the ids of the users matching the filter.
     * @param filter The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
     * @return Returns a list of all user ids matching the filter.
     * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun matchUsers(filter: Filter<User>): kotlin.collections.List<kotlin.String>

    /**
     * Load user ids from the database by filtering them using the provided Filter.
     * Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for Users are AllUsersFilter and UsersByIdsFilter. This method returns the list of the ids of the users matching the filter.
     * @param filter The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill
     * @return Returns a list of all user ids matching the filter.
     * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ServerException If the API returns a server error response
     */

}
