package io.icure.md.client.apis

import io.icure.kraken.client.extendedapis.DataOwnerApi
import io.icure.kraken.client.models.UserDto
import io.icure.kraken.client.models.decrypted.PatientDto
import io.icure.md.client.models.ApiInitialisationResult
import io.icure.md.client.models.AuthenticationProcess
import io.icure.md.client.models.AuthenticationResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.time.ExperimentalTime

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalTime
interface AuthenticationApi {
    suspend fun startAuthentication(
        healthcareProfessionalId: String,
        dataOwnerId: String,
        firstName: String,
        lastName: String,
        email: String,
        recaptcha: String,
        mobilePhone: String? = null
    ): AuthenticationProcess?

    suspend fun completeAuthentication(
        process: AuthenticationProcess,
        validationCode: String,
        patientKeyPair: Pair<String, String>,
        tokenAndKeyPairProvider: (String, String) -> Triple<String, String, String>?
    ): AuthenticationResult

    suspend fun initApiAndUserAuthenticationToken(
        process: AuthenticationProcess,
        validationCode: String,
        tokenAndKeyPairProvider: (String, String) -> Triple<String, String, String>?
    ): Pair<MedTechApi, ApiInitialisationResult>

    suspend fun initUserCrypto(
        api: MedTechApi,
        token: String,
        user: UserDto,
        patientKeyPair: Pair<String, String>
    ): MedTechApi

    suspend fun initPatientDelegationAndSave(
        apiWithNewKeyPair: MedTechApi,
        modPat: PatientDto,
        user: UserDto,
        dataOwnerApi: DataOwnerApi
    ): PatientDto
}
