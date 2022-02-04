package io.icure.md.client.apis.impl

import io.icure.kraken.client.models.ListOfIdsDto
import io.icure.md.client.apis.DeviceApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.isUUID
import io.icure.md.client.mappers.toDeviceDto
import io.icure.md.client.mappers.toMedicalDevice
import io.icure.md.client.models.Filter
import io.icure.md.client.models.MedicalDevice
import io.icure.md.client.models.PaginatedListMedicalDevice
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class DeviceApiImpl(private val api: MedTechApi) : DeviceApi {

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
        val medicalDevicesToCreate = medicalDevice.filter { it.rev == null }
        val medicalDevicesToUpdate = medicalDevice - medicalDevicesToCreate

        if (medicalDevicesToUpdate.any { it.id == null || !it.id.isUUID() }) {
            throw IllegalArgumentException("Update id should be provided as an UUID")
        }

        val devicesToCreate = medicalDevicesToCreate.map { device -> device.toDeviceDto() }
        val devicesToUpdate = medicalDevicesToUpdate.map { device -> device.toDeviceDto() }

        return (api.deviceApi().createDevices(devicesToCreate) + api.deviceApi()
            .updateDevices(devicesToUpdate)).mapNotNull { it.id }.let { ids ->
            api.deviceApi().getDevices(ListOfIdsDto(ids)).map { deviceDto -> deviceDto.toMedicalDevice() }
        }
    }

    /**
     * Deletes a medical device based on its [id]
     *
     * @param id id of the medical device to delete
     * @return id of the deleted device
     */
    override suspend fun deleteMedicalDevice(id: String): String {
        return api.deviceApi().deleteDevice(id).id!!
    }

    /**
     * Deletes a batch of medical device based on their ids
     *
     * @param requestBody ids of the medical devices to delete
     * @return ids of the deleted devices
     */
    override suspend fun deleteMedicalDevices(requestBody: List<String>): List<String> {
        return api.deviceApi().deleteDevices(ListOfIdsDto(ids = requestBody)).mapNotNull { it.id }
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
        return api.deviceApi().getDevice(id).toMedicalDevice()
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
}
