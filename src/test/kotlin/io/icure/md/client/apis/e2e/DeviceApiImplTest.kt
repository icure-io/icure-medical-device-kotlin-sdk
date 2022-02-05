package io.icure.md.client.apis.e2e

import io.icure.kraken.client.infrastructure.Diff
import io.icure.kraken.client.infrastructure.differences
import io.icure.kraken.client.infrastructure.filterDiffs
import io.icure.md.client.apis.MedicalDeviceApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.impl.MedicalDeviceApiImpl
import io.icure.md.client.models.MedicalDevice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@DisplayName("MedicalDevice tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DeviceApiImplTest {

    private val iCurePath = "https://kraken.icure.dev"
    private val authHeader = TestUtils.basicAuthFrom(".credentials")
    private val healthcareProfessionalId = "782f1bcd-9f3f-408a-af1b-cd9f3f908a98"
    private val healthcareProfessionalPrivateKey =
        TestUtils.healthcareProfessionalPrivateKey(healthcareProfessionalId, this::class.java)
    private val healthcareProfessionalPublicKey =
        runBlocking { TestUtils.healthcareProfessionalPublicKey(iCurePath, authHeader, healthcareProfessionalId) }

    private val medTechApi = MedTechApi.Builder()
        .iCureUrlPath(iCurePath)
        .authorization(authHeader)
        .addKeyPair(healthcareProfessionalId, healthcareProfessionalPublicKey, healthcareProfessionalPrivateKey)
        .build()

    private val testedInstance: MedicalDeviceApi = MedicalDeviceApiImpl(medTechApi)

    @Test
    @DisplayName("Create device test - HappyFlow")
    fun createDeviceHappyFlow() = runBlocking {
        val device = fairphoneMedicalDevice()
        val createdDevice = testedInstance.createOrModifyMedicalDevice(device)

        val diffs = device.differences(createdDevice)
        val filters = listOf("id", "author", "created", "modified", "responsible", "rev")
        val filteredDiffs = filterDiffs(createdDevice, device, diffs, filters)
        Assertions.assertEquals(emptyList<Diff>(), filteredDiffs)
    }

    @Test
    @DisplayName("Get device test - HappyFlow")
    fun getDeviceHappyFlow() = runBlocking {
        val device = iPhoneMedicalDevice()
        val createdDevice = testedInstance.createOrModifyMedicalDevice(device)
        val gotDevice = testedInstance.getMedicalDevice(createdDevice.id!!)

        val diffs = createdDevice.differences(gotDevice)
        Assertions.assertEquals(emptyList<Diff>(), diffs)
    }

    private fun fairphoneMedicalDevice() = MedicalDevice(
        name = "OKCardio",
        type = "Smartphone",
        brand = "Fairphone",
        model = "4"
    )

    private fun iPhoneMedicalDevice() = MedicalDevice(
        name = "OKCardio",
        type = "Smartphone",
        brand = "Apple",
        model = "iPhone 13 Pro"
    )
}
