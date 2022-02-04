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
import io.icure.md.client.models.Filter
import io.icure.md.client.models.MedicalDevice
import io.icure.md.client.models.PaginatedListMedicalDevice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import javax.inject.Named

@Named
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
interface DeviceApi {

    /**
    * Create or update a [MedicalDevice]
    * When modifying a device, you must ensure that the rev obtained when getting or creating the device is present as the rev is used to guarantee that the device has not been modified by a third party.
    * @param medicalDevice  
    * @return Returns the created or modified device as a [MedicalDevice] object, with an updated rev.
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun createOrModifyMedicalDevice(medicalDevice: MedicalDevice) : MedicalDevice 

    /**
    * Create or update a batch of [MedicalDevice]
    * When modifying a device, you must ensure that the rev obtained when getting or creating the device is present as the rev is used to guarantee that the device has not been modified by a third party.
    * @param medicalDevice  
    * @return Returns the created or modified devices as a list of [MedicalDevice] objects, with an updated rev.
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun createOrModifyMedicalDevices(medicalDevice: kotlin.collections.List<MedicalDevice>) : kotlin.collections.List<MedicalDevice> 

    /**
    * Delete a [MedicalDevice]
    * Deletes the medical device identified by the provided unique [medicalDeviceId].
    * @param medicalDeviceId  
    * @return Returns the rev of the deleted object.
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws ClientException if there is no medical device with the provided [medicalDeviceId].
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun deleteMedicalDevice(medicalDeviceId: kotlin.String) : kotlin.String 

    /**
    * Delete a batch of [MedicalDevice]
    * Deletes the batch of medical device identified by the provided [medicalDeviceIds].
    * @param requestBody  
    * @return Returns the rev of the deleted object.
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws ClientException if there is no medical device with the provided [medicalDeviceIds].
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun deleteMedicalDevices(requestBody: kotlin.collections.List<kotlin.String>) : kotlin.collections.List<kotlin.String> 

    /**
    * Load devices from the database by filtering them using the provided [filter].
    * Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for [MedicalDevice] are AllDevicesFilter and DevicesByIdsFilter. This method returns a paginated list of medical devices (with a cursor that lets you query the following items).
    * @param filter The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill 
    * @return Returns a PaginatedList of [MedicalDevice].
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun filterMedicalDevices(filter: Filter) : PaginatedListMedicalDevice 

    /**
    * Get a Medical Device
    * Each medical device is uniquely identified by a device id. The device id is a UUID. This [medicalDeviceId] is the preferred method to retrieve one specific device.
    * @param medicalDeviceId  
    * @return Returns the fetched device as a [MedicalDevice] object
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws ClientException if there is no device with the provided [medicalDeviceId].
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun getMedicalDevice(medicalDeviceId: kotlin.String) : MedicalDevice 

    /**
    * Load medical device ids from the database by filtering them using the provided Filter.
    * Filters are complex selectors that are built by combining basic building blocks. Examples of filters available for Users are AllUsersFilter and UsersByIdsFilter. This method returns the list of the ids of the users matching the filter.
    * @param filter The Filter object that describes which condition(s) the elements whose the ids should be returned must fulfill 
    * @return Returns a list of all medical device ids matching the filter.
    * @throws ClientException if you make this call without providing an authentication token (BASIC, SesssionId).
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    suspend fun matchMedicalDevices(filter: Filter) : kotlin.collections.List<kotlin.String> 

}
