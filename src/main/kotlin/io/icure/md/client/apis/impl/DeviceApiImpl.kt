package io.icure.md.client.apis.impl

import io.icure.kraken.client.models.ListOfIdsDto
import io.icure.md.client.apis.DeviceApi
import io.icure.md.client.isUUID
import io.icure.md.client.mappers.toDeviceDto
import io.icure.md.client.mappers.toMedicalDevice
import io.icure.md.client.models.Filter
import io.icure.md.client.models.MedicalDevice
import io.icure.md.client.models.PaginatedListMedicalDevice
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class DeviceApiImpl(private val api: io.icure.kraken.client.apis.DeviceApi) : DeviceApi {

    /**
     * Creates or modifies a [MedicalDevice]
     *
     * Update is done when the rev is passed
     *
     * @param medicalDevice [MedicalDevice] to process
     * @throws IllegalArgumentException if the provided id is invalid
     * @return Processed medical device
     */
    override suspend fun createOrModifyMedicalDevice(medicalDevice: MedicalDevice): MedicalDevice {
        return createOrModifyMedicalDevices(listOf(medicalDevice)).singleOrNull()
            ?: throw IllegalStateException("Error while creating/modifying device")
    }

    /**
     * Creates or modifies a list of [MedicalDevice]
     *
     * Update is done when the rev is passed
     *
     * @param medicalDevice List of [MedicalDevice] to process
     * @throws IllegalArgumentException if the provided id is invalid
     * @return A list of processed [MedicalDevice]
     */
    override suspend fun createOrModifyMedicalDevices(medicalDevice: List<MedicalDevice>): List<MedicalDevice> {
        val medicalDevicesToCreate = medicalDevice.filter { it.rev != null }
        val medicalDevicesToUpdate = medicalDevice - medicalDevicesToCreate

        if (medicalDevicesToUpdate.any { it.id == null || !it.id.isUUID() }){
            throw IllegalArgumentException("Update id should be provided as an UUID")
        }

        val devicesToCreate = medicalDevicesToCreate.map { device -> device.toDeviceDto() }
        val devicesToUpdate = medicalDevicesToUpdate.map { device -> device.toDeviceDto() }

        return (api.createDevices(devicesToCreate) + api.updateDevices(devicesToUpdate)).mapNotNull { it.id }.let { ids ->
            api.getDevices(ListOfIdsDto(ids)).map { deviceDto -> deviceDto.toMedicalDevice() }
        }
    }

    /**
     * Deletes a medical device based on its [id]
     *
     * @param id id of the medical device to delete
     * @return id of the deleted device
     */
    override suspend fun deleteMedicalDevice(id: String): String {
        return api.deleteDevice(id).id!!
    }

    /**
     * Deletes a batch of medical device based on their ids
     *
     * @param requestBody ids of the medical devices to delete
     * @return ids of the deleted devices
     */
    override suspend fun deleteMedicalDevices(requestBody: List<String>): List<String> {
        return api.deleteDevices(ListOfIdsDto(ids = requestBody)).mapNotNull { it.id }
    }

    override suspend fun filterMedicalDevices(filter: Filter): PaginatedListMedicalDevice {
        TODO()
    }

    /**
     * Get a [MedicalDevice] based on its id
     *
     * @param id id of the [MedicalDevice]
     * @return [MedicalDevice]
     */
    override suspend fun getMedicalDevice(id: String): MedicalDevice {
        return api.getDevice(id).toMedicalDevice()
    }

    /**
     * Gets [MedicalDevice] ids based on [filter]
     *
     * @param filter Search [Filter]
     * @return list of matched ids
     */
    override suspend fun matchMedicalDevices(filter: Filter): List<String> {
        TODO()
    }

    data class Builder(
        var defaultBasePath: String? = null,
        var authHeader: String? = null
    ) {
        fun defaultBasePath(defaultBasePath: String) = apply { this.defaultBasePath = defaultBasePath }
        fun authHeader(authHeader: String) = apply { this.authHeader = authHeader }
        fun build() = DeviceApiImpl(io.icure.kraken.client.apis.DeviceApi(basePath = defaultBasePath ?: throw IllegalArgumentException("defaultBasePath is required"), authHeader = authHeader ?: throw IllegalArgumentException("authHeader is required")))
    }
}
