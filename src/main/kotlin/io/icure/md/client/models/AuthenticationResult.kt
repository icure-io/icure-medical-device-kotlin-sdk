package io.icure.md.client.models

import io.icure.md.client.apis.MedTechApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.security.KeyPair

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@FlowPreview
data class AuthenticationResult(
    val medTechApi: MedTechApi,
    val keyPair: KeyPair,
    val token: String,
    val groupId: String,
    val userId: String
)
