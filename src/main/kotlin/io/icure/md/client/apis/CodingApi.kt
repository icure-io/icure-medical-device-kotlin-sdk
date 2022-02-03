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

import io.icure.md.client.models.Coding
import io.icure.md.client.models.Filter
import io.icure.md.client.models.PaginatedListCoding

import kotlinx.coroutines.ExperimentalCoroutinesApi
 
import io.icure.md.client.infrastructure.ClientException
import io.icure.md.client.infrastructure.ServerException

import kotlinx.coroutines.flow.flowOf
import java.nio.ByteBuffer
import java.util.*
import javax.inject.Named
import kotlinx.coroutines.flow.Flow
import java.net.URLEncoder

@Named
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
interface CodingApi {

    /**
    * Create a Coding
    * 
    * @param coding  
    * @return Coding
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun createOrModifyCoding(coding: Coding) : Coding 

    /**
    * Create a Coding
    * 
    * @param coding  
    * @return kotlin.collections.List<Coding>
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun createOrModifyCodings(coding: kotlin.collections.List<Coding>) : kotlin.collections.List<Coding> 

    /**
    * Delete a Coding
    * 
    * @param id  
    * @return kotlin.String
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun deleteCoding(id: kotlin.String) : kotlin.String 

    /**
    * Find Codings using a filter
    * 
    * @param filter  
    * @return PaginatedListCoding
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun filterCoding(filter: Filter) : PaginatedListCoding 

    /**
    * Get a Coding
    * 
    * @param id  
    * @return Coding
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun getCoding(id: kotlin.String) : Coding 

    /**
    * Find Codings using a filter
    * 
    * @param filter  
    * @return kotlin.collections.List<kotlin.String>
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun matchCoding(filter: Filter) : kotlin.collections.List<kotlin.String> 

}
