package io.icure.md.client.apis.e2e

import io.icure.diffutils.Diff
import io.icure.diffutils.differences
import io.icure.diffutils.filterDiffs
import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.extendedapis.getPatient
import io.icure.kraken.client.extendedapis.initDelegations
import io.icure.kraken.client.extendedapis.modifyPatient
import io.icure.md.client.apis.MedTechApi
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

    @Test
    @DisplayName("Create patient test - HappyFlow")
    fun createPatientHappyFlow() = runBlocking {
        val patient = patient()
        val patCred = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
        val api = patCred.api

        val createdPatient = api.patientApi().createOrModifyPatient(patient)

        val diffs = patient.differences(createdPatient)
        val filters = listOf("id", "author", "created", "modified", "responsible", "rev", "names", "systemMetaData")
        val filteredDiffs = filterDiffs(createdPatient, patient, diffs, filters)
        Assertions.assertEquals(emptyList<Diff>(), filteredDiffs)
    }

    @Test
    @DisplayName("Get patient test - HappyFlow")
    fun getPatientHappyFlow() = runBlocking {
        val patient = patient()
        val patCred = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
        val api = patCred.api

        val createdPatient = api.patientApi().createOrModifyPatient(patient)
        val gotDevice = api.patientApi().getPatient(createdPatient.id!!)

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

    @Test
    fun sharingDelegationPatientToHcp() {
        runBlocking {
            val patCred = TestUtils.UserCredentials.fromFile("pat_0857c725-3837-49ca-a3b6-f31cf7ebc61f.json")
            val hcpCred = TestUtils.UserCredentials.fromFile("hcp_2c5f952e-512b-4fd3-bc6d-0f66c282c159.json")

            val currentPatUser = patCred.api.userApi().getLoggedUser()
            val currentHcpUser = hcpCred.api.userApi().getLoggedUser()

            val patientFromPat = patCred.api.patientApi()
                .getPatient(currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient"))
            val delegatedPatient = patCred.api.patientApi().giveAccessTo(
                patientFromPat,
                currentHcpUser.healthcarePartyId ?: throw IllegalArgumentException("User must be a HCP")
            )

            assert(delegatedPatient.systemMetaData!!.delegations.containsKey(currentHcpUser.healthcarePartyId))
            assert(delegatedPatient.systemMetaData!!.encryptionKeys.containsKey(currentHcpUser.healthcarePartyId))

            val patientFromHcp = hcpCred.api.patientApi().getPatient(patientFromPat.id!!)
            assert(patientFromHcp == delegatedPatient)
        }
    }
}
