package io.icure.md.client.apis.e2e

import io.icure.kraken.client.crypto.CryptoUtils
import io.icure.kraken.client.crypto.publicKeyAsString
import io.icure.md.client.apis.AnonymousMedTechApi
import io.icure.md.client.apis.infrastructure.MailUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalTime
@ExperimentalUnsignedTypes
@DisplayName("User tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserApiImplTest {
    @Test
    @DisplayName("Creating a patient account on its own")
    fun selfCreatedUserPatient() {
        runBlocking {
            val anonymousMedTechApi =
                AnonymousMedTechApi.Builder().authProcessId(
                    authProcessId = System.getenv("TEST_PAT_AUTH_PROCESS_ID") ?: "6a355458dbfa392cb5624403190c39e5"
                )
                    .build()

            val emailAddress = MailUtils.getRandomEmailAddress()
            val userName = UUID.randomUUID().toString().take(8)
            val process = anonymousMedTechApi.authenticationApi.startAuthentication(
                System.getenv("TEST_HCP_ID"),
                userName,
                "",
                emailAddress,
                "a58afe0e-02dc-431b-8155-0351140099e4"
            )

            delay(10000)
            val email = MailUtils.readInbox(emailAddress).firstOrNull()
            assert(email != null)

            val validationCode = email!!.mail_subject.takeLast(6)
            val keyPair = CryptoUtils.generateKeyPairRSA()

            val tokenAndKeyProvider: ((String, String) -> Triple<String, String, String>?) =
                { _: String, _: String ->
                    null
                }
            val result = anonymousMedTechApi.authenticationApi.completeAuthentication(
                process,
                validationCode,
                keyPair,
                tokenAndKeyProvider
            )

            val api = result.medTechApi
            val currentUser = api.userApi().getLoggedUser()
            assert(currentUser.patientId != null)
            assert(currentUser.email == emailAddress)

            val patient = api.patientApi().getPatient(currentUser.patientId!!)
            assert(patient.id == currentUser.patientId)
            assert(patient.firstName == userName)
            assert(patient.lastName == "")
            assert(patient.publicKey == keyPair.publicKeyAsString())
            assert(patient.id in patient.systemMetaData!!.delegations.keys)
            assert(patient.id in patient.systemMetaData!!.encryptionKeys.keys)
            assert(patient.id in patient.systemMetaData!!.hcPartyKeys.keys)
        }
    }

    @Test
    fun selfCreatedUserHcp() {
        runBlocking {
            val anonymousMedTechApi =
                AnonymousMedTechApi.Builder()
                    .authProcessId(
                        authProcessId = System.getenv("TEST_HCP_AUTH_PROCESS_ID") ?: "6a355458dbfa392cb5624403190c6a19"
                    )
                    .build()

            val emailAddress = MailUtils.getRandomEmailAddress()
            val userName = UUID.randomUUID().toString().take(8)
            val process = anonymousMedTechApi.authenticationApi.startAuthentication(
                System.getenv("TEST_HCP_ID"),
                userName,
                "",
                emailAddress,
                "a58afe0e-02dc-431b-8155-0351140099e4"
            )

            delay(10000)
            val email = MailUtils.readInbox(emailAddress).firstOrNull()
            assert(email != null)

            val validationCode = email!!.mail_subject.takeLast(6)
            val keyPair = CryptoUtils.generateKeyPairRSA()

            val tokenAndKeyProvider: ((String, String) -> Triple<String, String, String>?) =
                { _: String, _: String ->
                    null
                }
            val result = anonymousMedTechApi.authenticationApi.completeAuthentication(
                process,
                validationCode,
                keyPair,
                tokenAndKeyProvider
            )

            val api = result.medTechApi
            val currentUser = api.userApi().getLoggedUser()
            assert(currentUser.email == emailAddress)

            val hcp = api.healthcareProfessionalApi().getHealthcareProfessional(currentUser.healthcarePartyId!!)
            assert(hcp.id == currentUser.healthcarePartyId)
            assert(hcp.firstName == userName)
            assert(hcp.lastName == "")
        }
    }

    @Test
    @DisplayName("Getting itself its user by email")
    fun getItselfByEmail() {
        runBlocking {
            // For HCP
            val hcpApi = TestUtils.simpleApiBasedOn(
                TestUtils.basicAuthFrom(
                    System.getenv("TEST_HCP_USERNAME"),
                    System.getenv("TEST_HCP_PASSWORD")
                )
            )
            val currentUser = hcpApi.userApi().getLoggedUser()
            val userByEmail = hcpApi.userApi().getUserByEmail(currentUser.email!!)

            assert(userByEmail == currentUser)

            // For Patient
            val patApi = TestUtils.simpleApiBasedOn(
                TestUtils.basicAuthFrom(
                    System.getenv("TEST_PAT_USERNAME"),
                    System.getenv("TEST_PAT_PASSWORD")
                )
            )
            val currentPatUser = patApi.userApi().getLoggedUser()
            val patUserByEmail = patApi.userApi().getUserByEmail(currentPatUser.email!!)

            assert(patUserByEmail == currentPatUser)
        }
    }

    @Test
    @DisplayName("Getting the user of a patient as HCP")
    fun getUserOfAPatientAsHCP() {
        runBlocking {
            val patApi = TestUtils.simpleApiBasedOn(
                TestUtils.basicAuthFrom(
                    System.getenv("TEST_PAT_USERNAME"),
                    System.getenv("TEST_PAT_PASSWORD")
                )
            )
            val hcpApi = TestUtils.simpleApiBasedOn(
                TestUtils.basicAuthFrom(
                    System.getenv("TEST_HCP_USERNAME"),
                    System.getenv("TEST_HCP_PASSWORD")
                )
            )

            val patCurrentUser = patApi.userApi().getLoggedUser()
            val patUserByEmailFromHcp = hcpApi.userApi().getUserByEmail(patCurrentUser.email!!)

            assert(patUserByEmailFromHcp == patCurrentUser)
        }
    }
}

