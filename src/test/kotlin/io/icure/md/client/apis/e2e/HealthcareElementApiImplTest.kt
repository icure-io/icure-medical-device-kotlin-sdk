package io.icure.md.client.apis.e2e

import io.icure.diffutils.Diff
import io.icure.diffutils.differences
import io.icure.diffutils.filterDiffs
import io.icure.md.client.mappers.dataOwnerId
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
@ExperimentalUnsignedTypes
@DisplayName("MedicalDevice tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HealthcareElementApiImplTest {

    @Test
    @DisplayName("Create healthcare element test - HappyFlow")
    fun createHealthcareElementHappyFlow() = runBlocking {
        val api = TestUtils.patApiBasedOn()
        val currentUser = api.userApi().getLoggedUser()

        val he = healthElement()
        val createdHe = api.healthcareElementApi().createOrModifyHealthcareElement(currentUser.dataOwnerId(), he)

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
        val api = TestUtils.patApiBasedOn()
        val currentUser = api.userApi().getLoggedUser()

        val he = healthElement()
        val createdHe = api.healthcareElementApi().createOrModifyHealthcareElement(currentUser.dataOwnerId(), he)
        val gotHe = api.healthcareElementApi().getHealthcareElement(createdHe.id!!)

        val diffs = createdHe.differences(gotHe)
        Assertions.assertEquals(emptyList<Diff>(), diffs)
    }

    @Test
    @DisplayName("Sharing delegation of DecryptedHealthElementDto patient to HCP and HCP to HCP")
    fun shareDelegationOhHealthCareElementFromPatientToHcp() {
        runBlocking {
            val patApi = TestUtils.patApiBasedOn()
            val hcpApi1 = TestUtils.hcpApiBasedOn(
                TestUtils.basicAuthFrom(
                    System.getenv("TEST_HCP_USERNAME"),
                    System.getenv("TEST_HCP_PASSWORD")
                ),
                System.getenv("TEST_HCP_ID"),
                System.getenv("TEST_HCP_PRIV_KEY")
            )
            val hcpApi2 = TestUtils.hcpApiBasedOn(
                TestUtils.basicAuthFrom(
                    System.getenv("TEST_HCP_2_USERNAME"),
                    System.getenv("TEST_HCP_2_PASSWORD")
                ),
                System.getenv("TEST_HCP_2_ID"),
                System.getenv("TEST_HCP_2_PRIV_KEY")
            )

            val currentPatUser = patApi.userApi().getLoggedUser()
            val currentHcpUser = hcpApi1.userApi().getLoggedUser()
            val currentHcpUser2 = hcpApi2.userApi().getLoggedUser()

            val patientFromPat = patApi.patientApi()
                .getPatient(currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient"))
            val createdHEFromPatient =
                patApi.healthcareElementApi().createOrModifyHealthcareElement(patientFromPat.id!!, healthElement())
            val sharedHE = patApi.healthcareElementApi().giveAccessTo(
                createdHEFromPatient,
                currentHcpUser.healthcarePartyId ?: throw IllegalArgumentException("User must be a HCP")
            )
            val gotHEFromHCP1 = hcpApi1.healthcareElementApi().getHealthcareElement(sharedHE.id!!)

            assertThrows<Exception> { hcpApi2.healthcareElementApi().getHealthcareElement(sharedHE.id!!) }
            Assertions.assertEquals(sharedHE, gotHEFromHCP1)

            val sharedHEFromHCP1 =
                hcpApi1.healthcareElementApi().giveAccessTo(gotHEFromHCP1, currentHcpUser2.dataOwnerId())
            val gotHEFromHCP2 = hcpApi2.healthcareElementApi().getHealthcareElement(sharedHE.id!!)

            Assertions.assertEquals(sharedHEFromHCP1, gotHEFromHCP2)
        }
    }

    @Test
    @DisplayName("Sharing delegation of DecryptedHealthElementDto HCP to Patient")
    fun shareDelegationOhHealthCareElementFromHcpToPatient() {
        runBlocking {
            val patApi = TestUtils.patApiBasedOn()
            val hcpApi1 = TestUtils.hcpApiBasedOn(
                TestUtils.basicAuthFrom(
                    System.getenv("TEST_HCP_USERNAME"),
                    System.getenv("TEST_HCP_PASSWORD")
                ),
                System.getenv("TEST_HCP_ID"),
                System.getenv("TEST_HCP_PRIV_KEY")
            )
            val hcpApi2 = TestUtils.hcpApiBasedOn(
                TestUtils.basicAuthFrom(
                    System.getenv("TEST_HCP_2_USERNAME"),
                    System.getenv("TEST_HCP_2_PASSWORD")
                ),
                System.getenv("TEST_HCP_2_ID"),
                System.getenv("TEST_HCP_2_PRIV_KEY")
            )

            val currentPatUser = patApi.userApi().getLoggedUser()
            val currentHcpUser = hcpApi1.userApi().getLoggedUser()

            val patientFromPat = patApi.patientApi()
                .getPatient(currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient"))
            val delegatedPatient = patApi.patientApi().giveAccessTo(
                patientFromPat,
                currentHcpUser.healthcarePartyId ?: throw IllegalArgumentException("User must be a HCP")
            )

            val createdHEFromHCP1 =
                hcpApi1.healthcareElementApi()
                    .createOrModifyHealthcareElement(patientFromPat.id!!, healthElement())
            assertThrows<Exception> { patApi.healthcareElementApi().getHealthcareElement(createdHEFromHCP1.id!!) }

            val sharedHEFromHCP1 = hcpApi1.healthcareElementApi().giveAccessTo(
                createdHEFromHCP1,
                currentPatUser.patientId ?: throw IllegalArgumentException("User must be a Patient")
            )
            val gotHEFromPat = patApi.healthcareElementApi().getHealthcareElement(sharedHEFromHCP1.id!!)

            assertThrows<Exception> { hcpApi2.healthcareElementApi().getHealthcareElement(sharedHEFromHCP1.id!!) }
            Assertions.assertEquals(sharedHEFromHCP1, gotHEFromPat)
        }
    }

    private fun healthElement() = HealthcareElement(
        note = "Stay hungry. Stay foolish"
    )
}
