package io.icure.md.client.apis.e2e

import io.icure.diffutils.Diff
import io.icure.diffutils.differences
import io.icure.diffutils.filterDiffs
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
@ExperimentalUnsignedTypes
@ExperimentalTime
@DisplayName("Patient tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PatientApiImplTest {

    @Test
    @DisplayName("Create patient test - HappyFlow")
    fun createPatientHappyFlow() = runBlocking {
        val patient = patient()
        val api = TestUtils.hcpApiBasedOn()

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
        val api = TestUtils.hcpApiBasedOn(TestUtils.basicAuthFrom())

        val createdPatient = api.patientApi().createOrModifyPatient(patient)
        val gotPatient = api.patientApi().getPatient(createdPatient.id!!)

        val diffs = createdPatient.differences(gotPatient)
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
    fun sharingDelegationPatientToHcp() {
        runBlocking {
            val patApi = TestUtils.patApiBasedOn()
            val hcpApi = TestUtils.hcpApiBasedOn()

            val currentPatUser = patApi.userApi().getLoggedUser()
            val currentHcpUser = hcpApi.userApi().getLoggedUser()

            val patientFromPat = patApi.patientApi()
                .getPatient(currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient"))
            val delegatedPatient = patApi.patientApi().giveAccessTo(
                patientFromPat,
                currentHcpUser.healthcarePartyId ?: throw IllegalArgumentException("User must be a HCP")
            )

            assert(delegatedPatient.systemMetaData!!.delegations.containsKey(currentHcpUser.healthcarePartyId))
            assert(delegatedPatient.systemMetaData!!.encryptionKeys.containsKey(currentHcpUser.healthcarePartyId))

            val patientFromHcp = hcpApi.patientApi().getPatient(patientFromPat.id!!)
            assert(patientFromHcp == delegatedPatient)
        }
    }
}
