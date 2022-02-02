package io.icure.kraken.client.apis.impl

import io.icure.kraken.client.apis.DeviceApi
import io.icure.kraken.client.models.Filter
import io.icure.kraken.client.models.MedicalDevice
import io.icure.kraken.client.models.PaginatedListMedicalDevice
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class DeviceApiImpl : DeviceApi {
    override suspend fun createOrModifyMedicalDevice(medicalDevice: MedicalDevice): MedicalDevice {
        TODO("Not yet implemented")
    }

    override suspend fun createOrModifyMedicalDevices(medicalDevice: List<MedicalDevice>): List<MedicalDevice> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMedicalDevice(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMedicalDevices(requestBody: List<String>): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun filterMedicalDevices(filter: Filter): PaginatedListMedicalDevice {
        TODO("Not yet implemented")
    }

    override suspend fun getMedicalDevice(id: String): MedicalDevice {
        TODO("Not yet implemented")
    }

    override suspend fun matchMedicalDevices(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }
}
