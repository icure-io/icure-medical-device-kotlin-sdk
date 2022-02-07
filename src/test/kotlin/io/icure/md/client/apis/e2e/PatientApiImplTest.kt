package io.icure.md.client.apis.e2e

import io.icure.diffutils.Diff
import io.icure.diffutils.differences
import io.icure.diffutils.filterDiffs
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.PatientApi
import io.icure.md.client.apis.impl.PatientApiImpl
import io.icure.md.client.models.Patient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@DisplayName("Patient tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PatientApiImplTest {

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

    private val testedInstance: PatientApi = PatientApiImpl(medTechApi)

    @Test
    @DisplayName("Create patient test - HappyFlow")
    fun createDeviceHappyFlow() = runBlocking {
        val patient = patient()
        val createdPatient = testedInstance.createOrModifyPatient(patient)

        val diffs = patient.differences(createdPatient)
        val filters = listOf("id", "author", "created", "modified", "responsible", "rev", "names")
        val filteredDiffs = filterDiffs(createdPatient, patient, diffs, filters)
        Assertions.assertEquals(emptyList<Diff>(), filteredDiffs)
    }

    @Test
    @DisplayName("Get patient test - HappyFlow")
    fun getDeviceHappyFlow() = runBlocking {
        val patient = patient()
        val createdPatient = testedInstance.createOrModifyPatient(patient)
        val gotDevice = testedInstance.getPatient(createdPatient.id!!)

        val diffs = createdPatient.differences(gotDevice)
        Assertions.assertEquals(emptyList<Diff>(), diffs)
    }

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
