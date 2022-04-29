package io.icure.md.client.apis.e2e

import io.icure.diffutils.Diff
import io.icure.diffutils.differences
import io.icure.diffutils.filterDiffs
import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.extendedapis.getPatient
import io.icure.kraken.client.extendedapis.initDelegations
import io.icure.kraken.client.extendedapis.modifyPatient
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.PatientApi
import io.icure.md.client.apis.impl.PatientApiImpl
import io.icure.md.client.models.Patient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
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
    fun createPatientHappyFlow() = runBlocking {
        val patient = patient()
        val createdPatient = testedInstance.createOrModifyPatient(patient)

        val diffs = patient.differences(createdPatient)
        val filters = listOf("id", "author", "created", "modified", "responsible", "rev", "names", "systemMetaData")
        val filteredDiffs = filterDiffs(createdPatient, patient, diffs, filters)
        Assertions.assertEquals(emptyList<Diff>(), filteredDiffs)
    }

    @Test
    @DisplayName("Get patient test - HappyFlow")
    fun getPatientHappyFlow() = runBlocking {
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

    @Test
    fun modifyPatientForHk() = runBlocking {
        val hkId = "171f186a-7a2a-40f0-b842-b486428c771b"
        val hkAuth = TestUtils.UsernamePassword("kinomobile@heartkinetics.com", "M6Rznf9uTjSuPxi2sY").toBasicAuth()
        val hkPrivateKey =
            TestUtils.healthcareProfessionalPrivateKey(hkId, this::class.java)
        val hkPublicKey =
            runBlocking { TestUtils.healthcareProfessionalPublicKey("http://127.0.0.1:16043", hkAuth, hkId) }
        val hkMedTechApi = MedTechApi.Builder()
            .iCureUrlPath("http://127.0.0.1:16043")
            .authorization(hkAuth)
            .addKeyPair(hkId, hkPublicKey, hkPrivateKey)
            .build()

        val patUserId = "0a98a656-cf5d-43c8-80bc-ed75ece8ca78"

        val patUser = hkMedTechApi.baseUserApi.getUser(patUserId)
        val adminUser = hkMedTechApi.baseUserApi.getCurrentUser()
        val pat = hkMedTechApi.basePatientApi.getPatient(
            adminUser,
            "a37e0a71-07d2-4414-9b2b-2120ae9a16fc",
            patientCryptoConfig(hkMedTechApi.localCrypto)
        )
        val initPat = pat.initDelegations(patUser, patientCryptoConfig(hkMedTechApi.localCrypto))
        val updatedPatient =
            hkMedTechApi.basePatientApi.modifyPatient(adminUser, initPat, patientCryptoConfig(hkMedTechApi.localCrypto))

        assert(updatedPatient != null)
    }
}
