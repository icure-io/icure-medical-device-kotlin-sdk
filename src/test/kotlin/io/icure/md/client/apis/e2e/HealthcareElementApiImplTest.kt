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
    @DisplayName("Sharing delegation of DecryptedHealthElementDto patient to HCP")
    fun shareDelegationOhHealthCareElementFromPatientToHcp() {
        runBlocking {
            val patCred = TestUtils.UserCredentials.fromFile("pat_e810366a-89b6-4cd5-a36a-41e002344e6c.json")
            val hcpCred = TestUtils.UserCredentials.fromFile("hcp_2c5f952e-512b-4fd3-bc6d-0f66c282c159.json")

            val currentPatUser = patCred.api.userApi().getLoggedUser()
            val currentHcpUser = hcpCred.api.userApi().getLoggedUser()

            val patientFromPat = patCred.api.patientApi()
                .getPatient(currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient"))
            val createdHEFromPatient =
                patCred.api.healthcareElementApi().createOrModifyHealthcareElement(patientFromPat.id!!, healthElement())
            val sharedHE = patCred.api.healthcareElementApi().giveAccessTo(
                createdHEFromPatient,
                currentHcpUser.healthcarePartyId ?: throw IllegalArgumentException("User must be a HCP")
            )
            val gotHEFromHcp = hcpCred.api.healthcareElementApi().getHealthcareElement(sharedHE.id!!)

            Assertions.assertEquals(sharedHE, gotHEFromHcp)
        }
    }

    private fun healthElement() = HealthcareElement(
        note = "Stay hungry. Stay foolish",
    )
}
