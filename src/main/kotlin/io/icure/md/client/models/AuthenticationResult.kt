package io.icure.md.client.models

import io.icure.md.client.apis.MedTechApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.time.ExperimentalTime

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalTime
data class AuthenticationResult(
    val medTechApi: MedTechApi,
    val keyPair: Pair<String, String>,
    val token: String,
    val groupId: String,
    val userId: String
)
