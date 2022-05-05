package io.icure.md.client.apis

import io.icure.md.client.apis.impl.AuthenticationApiImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.time.ExperimentalTime

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalTime
@FlowPreview
class AnonymousMedTechApi(
    iCureUrlPath: String,
    authServerUrl: String,
    authProcessId: String,
    defaultLanguage: String
) {
    val authenticationApi = AuthenticationApiImpl(iCureUrlPath, authServerUrl, authProcessId, defaultLanguage)

    data class Builder(
        private var iCureUrlPath: String = "https://kraken.icure.dev",
        private var authServerUrl: String = "https://msg-gw.icure.cloud/km",
        private var authProcessId: String? = null,
        private var defaultLanguage: String = "en"
    ) {
        fun iCureUrlPath(iCureUrlPath: String) = apply { this.iCureUrlPath = iCureUrlPath }
        fun authServerUrl(authServerUrl: String) = apply { this.authServerUrl = authServerUrl }
        fun authProcessId(authProcessId: String) = apply { this.authProcessId = authProcessId }
        fun defaultLanguage(defaultLanguage: String) = apply { this.defaultLanguage = defaultLanguage }
        fun build(): AnonymousMedTechApi {
            if (authProcessId == null) {
                throw IllegalArgumentException("You have to provide an authProcessId to be able to build an AnonymousMedTechApi")
            }

            return AnonymousMedTechApi(
                iCureUrlPath = iCureUrlPath,
                authServerUrl = authServerUrl,
                authProcessId = authProcessId!!,
                defaultLanguage = defaultLanguage
            )
        }
    }
}
