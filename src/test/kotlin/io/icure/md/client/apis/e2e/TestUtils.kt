package io.icure.md.client.apis.e2e

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.json.JsonReadFeature
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import io.icure.kraken.client.apis.HealthcarePartyApi
import io.icure.kraken.client.crypto.toPrivateKey
import io.icure.kraken.client.crypto.toPublicKey
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.io.File
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
object TestUtils {

    private val defaultHcpId = "782f1bcd-9f3f-408a-af1b-cd9f3f908a98"
    private val defaultObjectMapper: ObjectMapper = ObjectMapper()
        .registerModule(KotlinModule())
        .registerModule(JavaTimeModule()).apply {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
        }

    fun healthcareProfessionalPrivateKey(
        hcpId: String = defaultHcpId,
        clazz: Class<*> = this::class.java
    ): RSAPrivateKey = clazz.classLoader
        .getResource("io/icure/md/client/apis/impl/keys/$hcpId-icc-priv.2048.key")
        ?.readText()?.trim()?.toPrivateKey()
        ?: throw RuntimeException("Couldn't read HCP Key from file")

    suspend fun healthcareProfessionalPublicKey(
        basePath: String,
        authorization: String,
        hcpId: String
    ): RSAPublicKey {
        return HealthcarePartyApi(basePath = basePath, authHeader = authorization)
            .getPublicKey(hcpId)
            .hexString
            ?.toPublicKey()
            ?: throw RuntimeException("Couldn't read HCP Public Key")
    }

    fun basicAuthFrom(credentialsFilePath: String): String {
        val usernamePassword: UsernamePassword = defaultObjectMapper.readValue(File(credentialsFilePath).readText())!!
        return usernamePassword.toBasicAuth()
    }

    data class UsernamePassword(val username: String, val password: String) {
        fun toBasicAuth() = "Basic ${java.util.Base64.getEncoder().encodeToString("$username:$password".toByteArray())}"
    }
}