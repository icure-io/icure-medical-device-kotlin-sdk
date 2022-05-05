package io.icure.md.client.apis.e2e

import io.icure.diffutils.Diff
import io.icure.diffutils.differences
import io.icure.diffutils.filterDiffs
import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.extendedapis.createPatient
import io.icure.md.client.apis.HealthcareElementApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.impl.HealthcareElementApiImpl
import io.icure.md.client.mappers.toPatient
import io.icure.md.client.mappers.toPatientDto
import io.icure.md.client.models.HealthcareElement
import io.icure.md.client.models.Patient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalTime
@DisplayName("MedicalDevice tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HealthcareElementApiImplTest {

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

    private val testedInstance: HealthcareElementApi = HealthcareElementApiImpl(medTechApi)
    private val patientId = runBlocking {
        createPatientFlow().single().id ?: throw Exception("Patient should be created before Healthcare Element tests")
    }

    private fun createPatientFlow() = flow<Patient> {
        emit(
            medTechApi.basePatientApi.createPatient(
                medTechApi.baseUserApi.getCurrentUser(),
                patient().toPatientDto(),
                patientCryptoConfig(medTechApi.localCrypto)
            ).toPatient()
        )
    }

    @Test
    @DisplayName("Create healthcare element test - HappyFlow")
    fun createHealthcareElementHappyFlow() = runBlocking {
        val he = healthElement()
        val createdHe = testedInstance.createOrModifyHealthcareElement(patientId, he)

        val diffs = he.differences(createdHe)
        val filters = listOf(
            "id",
            "author",
            "created",
            "modified",
            "responsible",
            "rev",
            "openingDate",
            "systemMetaData",
            "healthElementId",
            "valueDate"
        )
        val filteredDiffs = filterDiffs(createdHe, he, diffs, filters)
        Assertions.assertEquals(emptyList<Diff>(), filteredDiffs)
    }

    @Test
    @DisplayName("Get healthcare element test - HappyFlow")
    fun getHealthcareElementHappyFlow() = runBlocking {
        val he = healthElement()
        val createdHe = testedInstance.createOrModifyHealthcareElement(patientId, he)
        val gotHe = testedInstance.getHealthcareElement(createdHe.id!!)

        val diffs = createdHe.differences(gotHe)
        Assertions.assertEquals(emptyList<Diff>(), diffs)
    }

    private fun healthElement() = HealthcareElement(
        note = "This is a test",
    )

    private fun patient() = Patient(
        id = UUID.randomUUID().toString(),
        firstName = "John",
        lastName = "Doe",
        note = "To be encrypted",
        birthSex = Patient.BirthSex.male,
        gender = Patient.Gender.male,
        personalStatus = Patient.PersonalStatus.single
    )
}
