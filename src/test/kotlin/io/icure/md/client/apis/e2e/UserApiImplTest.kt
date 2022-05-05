package io.icure.md.client.apis.e2e

import io.icure.kraken.client.crypto.CryptoUtils
import io.icure.kraken.client.crypto.privateKeyAsString
import io.icure.kraken.client.crypto.publicKeyAsString
import io.icure.md.client.apis.AnonymousMedTechApi
import io.icure.md.client.apis.infrastructure.MailUtils
import io.icure.md.client.mappers.dataOwnerId
import io.icure.md.client.models.User
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
    fun selfCreatedUserPatient() {
        runBlocking {
            val anonymousMedTechApi =
                AnonymousMedTechApi.Builder().authProcessId(authProcessId = "f0ced6c6-d7cb-4f78-841e-2674ad09621e")
                    .build()

            val emailAddress = MailUtils.getRandomEmailAddress()
            val userName = UUID.randomUUID().toString().take(8)
            val process = anonymousMedTechApi.authenticationApi.startAuthentication(
                "171f186a-7a2a-40f0-b842-b486428c771b",
                userName,
                "",
                emailAddress,
                "a58afe0e-02dc-431b-8155-0351140099e4"
            )

            println("UserName: $userName")

            println("ProcessId: ${process!!.requestId}")
            println("Login: ${process.login}")

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

            val patient = api.patientApi().getPatient(currentUser.patientId!!)
            assert(patient.id in patient.systemMetaData!!.delegations.keys)
            assert(patient.id in patient.systemMetaData!!.encryptionKeys.keys)
            assert(patient.id in patient.systemMetaData!!.hcPartyKeys.keys)


            writeFile(
                currentUser, TestUtils.UserCredentials(
                    emailAddress,
                    result.token,
                    currentUser.dataOwnerId(),
                    result.keyPair.publicKeyAsString(),
                    result.keyPair.privateKeyAsString()
                )
            )
        }
    }

    @Test
    fun selfCreatedUserHcp() {
        runBlocking {
            val anonymousMedTechApi =
                AnonymousMedTechApi.Builder().authProcessId(authProcessId = "6a355458dbfa392cb56244031907f47a").build()

            val emailAddress = MailUtils.getRandomEmailAddress()
            val userName = UUID.randomUUID().toString().take(8)
            val process = anonymousMedTechApi.authenticationApi.startAuthentication(
                "171f186a-7a2a-40f0-b842-b486428c771b",
                userName,
                "",
                emailAddress,
                "a58afe0e-02dc-431b-8155-0351140099e4"
            )

            println("UserName: $userName")

            println("ProcessId: ${process.requestId}")
            println("Login: ${process.login}")

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

            writeFile(
                currentUser, TestUtils.UserCredentials(
                    emailAddress,
                    result.token,
                    currentUser.dataOwnerId(),
                    result.keyPair.publicKeyAsString(),
                    result.keyPair.privateKeyAsString()
                )
            )
        }
    }

    private fun writeFile(currentUser: User, credentials: TestUtils.UserCredentials) {
        TestUtils.writeUserCredentials(
            credentials,
            currentUser.patientId?.let { "pat_" } ?: currentUser.healthcarePartyId?.let { "hcp_" }
            ?: currentUser.deviceId?.let { "dev_" } ?: "s"
        )
    }

}

