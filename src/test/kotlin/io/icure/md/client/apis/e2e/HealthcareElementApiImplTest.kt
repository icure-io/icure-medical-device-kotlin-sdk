package io.icure.md.client.apis.e2e

import io.icure.diffutils.Diff
import io.icure.diffutils.differences
import io.icure.diffutils.filterDiffs
import io.icure.md.client.models.HealthcareElement
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalTime
@DisplayName("MedicalDevice tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HealthcareElementApiImplTest {

    @Test
    @DisplayName("Create healthcare element test - HappyFlow")
    fun createHealthcareElementHappyFlow() = runBlocking {
        val patCred = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
        val api = patCred.api

        val he = healthElement()
        val createdHe = api.healthcareElementApi().createOrModifyHealthcareElement(patCred.dataOwnerId, he)

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
        val patCred = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
        val api = patCred.api

        val he = healthElement()
        val createdHe = api.healthcareElementApi().createOrModifyHealthcareElement(patCred.dataOwnerId, he)
        val gotHe = api.healthcareElementApi().getHealthcareElement(createdHe.id!!)

        val diffs = createdHe.differences(gotHe)
        Assertions.assertEquals(emptyList<Diff>(), diffs)
    }

    @Test
    @DisplayName("Sharing delegation of DecryptedHealthElementDto patient to HCP and HCP to HCP")
    fun shareDelegationOhHealthCareElementFromPatientToHcp() {
        runBlocking {
            val patCred = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
            val hcpCred1 = TestUtils.UserCredentials.fromFile("hcp_2c5f952e-512b-4fd3-bc6d-0f66c282c159.json")
            val hcpCred2 = TestUtils.UserCredentials.fromFile("hcp_0b464cfc-384a-4ad1-9264-28a1524ea09e.json")

            val currentPatUser = patCred.api.userApi().getLoggedUser()
            val currentHcpUser = hcpCred1.api.userApi().getLoggedUser()

            val patientFromPat = patCred.api.patientApi()
                .getPatient(currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient"))
            val createdHEFromPatient =
                patCred.api.healthcareElementApi().createOrModifyHealthcareElement(patientFromPat.id!!, healthElement())
            val sharedHE = patCred.api.healthcareElementApi().giveAccessTo(
                createdHEFromPatient,
                currentHcpUser.healthcarePartyId ?: throw IllegalArgumentException("User must be a HCP")
            )
            val gotHEFromHCP1 = hcpCred1.api.healthcareElementApi().getHealthcareElement(sharedHE.id!!)

            assertThrows<Exception> { hcpCred2.api.healthcareElementApi().getHealthcareElement(sharedHE.id!!) }
            Assertions.assertEquals(sharedHE, gotHEFromHCP1)

            val sharedHEFromHCP1 = hcpCred1.api.healthcareElementApi().giveAccessTo(gotHEFromHCP1, hcpCred2.dataOwnerId)
            val gotHEFromHCP2 = hcpCred2.api.healthcareElementApi().getHealthcareElement(sharedHE.id!!)

            Assertions.assertEquals(sharedHEFromHCP1, gotHEFromHCP2)
        }
    }

    @Test
    @DisplayName("Sharing delegation of DecryptedHealthElementDto HCP to Patient")
    fun shareDelegationOhHealthCareElementFromHcpToPatient() {
        runBlocking {
            val patCred = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
            val hcpCred1 = TestUtils.UserCredentials.fromFile("hcp_2c5f952e-512b-4fd3-bc6d-0f66c282c159.json")
            val hcpCred2 = TestUtils.UserCredentials.fromFile("hcp_0b464cfc-384a-4ad1-9264-28a1524ea09e.json")

            val currentPatUser = patCred.api.userApi().getLoggedUser()
            val currentHcpUser = hcpCred1.api.userApi().getLoggedUser()

            val patientFromPat = patCred.api.patientApi()
                .getPatient(currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient"))
            val delegatedPatient = patCred.api.patientApi().giveAccessTo(
                patientFromPat,
                currentHcpUser.healthcarePartyId ?: throw IllegalArgumentException("User must be a HCP")
            )

            val createdHEFromHCP1 =
                hcpCred1.api.healthcareElementApi()
                    .createOrModifyHealthcareElement(patientFromPat.id!!, healthElement())
            assertThrows<Exception> { patCred.api.healthcareElementApi().getHealthcareElement(createdHEFromHCP1.id!!) }

            val sharedHEFromHCP1 = hcpCred1.api.healthcareElementApi().giveAccessTo(
                createdHEFromHCP1,
                currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient")
            )
            val gotHEFromPat = patCred.api.healthcareElementApi().getHealthcareElement(sharedHEFromHCP1.id!!)

            assertThrows<Exception> { hcpCred2.api.healthcareElementApi().getHealthcareElement(sharedHEFromHCP1.id!!) }
            Assertions.assertEquals(sharedHEFromHCP1, gotHEFromPat)
        }
    }

    private fun healthElement() = HealthcareElement(
        note = "Stay hungry. Stay foolish",
    )
}
