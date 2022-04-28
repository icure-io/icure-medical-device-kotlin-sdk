package io.icure.md.client.apis.impl

import io.icure.kraken.client.models.ListOfIdsDto
import io.icure.kraken.client.models.filter.chain.FilterChain
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.MedicalDeviceApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.isUUID
import io.icure.md.client.mappers.toAbstractFilterDto
import io.icure.md.client.mappers.toDeviceDto
import io.icure.md.client.mappers.toMedicalDevice
import io.icure.md.client.mappers.toPaginatedListMedicalDevice
import io.icure.md.client.models.MedicalDevice
import io.icure.md.client.models.PaginatedListMedicalDevice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@FlowPreview
@ExperimentalTime
class MedicalDeviceApiImpl(private val medTechApi: MedTechApi) : MedicalDeviceApi {

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

        return (medTechApi.baseDeviceApi.createDevices(devicesToCreate) + medTechApi.baseDeviceApi
            .updateDevices(devicesToUpdate)).mapNotNull { it.id }.let { ids ->
            medTechApi.baseDeviceApi.getDevices(ListOfIdsDto(ids)).map { deviceDto -> deviceDto.toMedicalDevice() }
        }
    }

    /**
     * Deletes a medical device based on its [medicalDeviceId]
     *
     * @param medicalDeviceId id of the medical device to delete
     * @return id of the deleted device
     */
    override suspend fun deleteMedicalDevice(medicalDeviceId: String): String {
        return medTechApi.baseDeviceApi.deleteDevice(medicalDeviceId).rev
            ?: throw IllegalArgumentException("Invalid medical device id")
    }

    /**
     * Deletes a batch of medical device based on their ids
     *
     * @param requestBody ids of the medical devices to delete
     * @return ids of the deleted devices
     */
    override suspend fun deleteMedicalDevices(requestBody: List<String>): List<String> {
        return medTechApi.baseDeviceApi.deleteDevices(ListOfIdsDto(ids = requestBody)).mapNotNull { it.rev }
    }

    override suspend fun filterMedicalDevices(
        filter: Filter<MedicalDevice>,
        nextDeviceId: String?,
        limit: Int?
    ): PaginatedListMedicalDevice {
        return medTechApi.baseDeviceApi
            .filterDevicesBy(FilterChain(filter.toAbstractFilterDto(), null), nextDeviceId, limit)
            .toPaginatedListMedicalDevice()
    }

    /**
     * Get a [MedicalDevice] based on its id
     *
     * @param medicalDeviceId id of the [MedicalDevice]
     * @return [MedicalDevice]
     */
    override suspend fun getMedicalDevice(medicalDeviceId: String): MedicalDevice {
        return medTechApi.baseDeviceApi.getDevice(medicalDeviceId).toMedicalDevice()
    }

    /**
     * Gets [MedicalDevice] ids based on [filter]
     *
     * @param filter Search [Filter]
     * @return list of matched ids
     */
    override suspend fun matchMedicalDevices(filter: Filter<MedicalDevice>): List<String> {
        return medTechApi.baseDeviceApi.matchDevicesBy(filter.toAbstractFilterDto())
    }
}
