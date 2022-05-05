package io.icure.md.client.apis.infrastructure

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.json.JsonReadFeature
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

object MailUtils {
    private val API_KEY = System.getProperty("RAPID_API_KEY")
    private val httpClient = HttpClient.create().headers { h ->
        h.set("X-RapidAPI-Host", "privatix-temp-mail-v1.p.rapidapi.com")
        h.set("X-RapidAPI-Key", API_KEY)
    }
    private val objectMapper = ObjectMapper()
        .registerModule(KotlinModule())
        .registerModule(JavaTimeModule()).apply {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
        }

    suspend fun getRandomEmailAddress(): String {
        val domain = httpClient.get().uri("https://privatix-temp-mail-v1.p.rapidapi.com/request/domains/")
            .responseSingle { response, bytes ->
                if (response.status().code() < 400) {
                    bytes.asString().map { objectMapper.readValue(it, object : TypeReference<Array<String>>() {})!![0] }
                } else {
                    Mono.empty<String>()
                }
            }.awaitFirstOrNull()

        return "${UUID.randomUUID()}$domain"
    }

    suspend fun readInbox(email: String): Array<Email> {
        val md = MessageDigest.getInstance("MD5")
        val emailMd5 = BigInteger(1, md.digest(email.toByteArray())).toString(16).padStart(32, '0')

        return httpClient.get().uri("https://privatix-temp-mail-v1.p.rapidapi.com/request/mail/id/${emailMd5}/")
            .responseSingle { response, bytes ->
                if (response.status().code() < 400) {
                    bytes.asString().map { objectMapper.readValue(it, object : TypeReference<Array<Email>>() {})!! }
                } else {
                    throw IllegalStateException("No emails found")
                }
            }.awaitFirst()
    }

    data class Email(
        val _id: Any,
        val mail_subject: String,
        val createdAt: Any,
        val mail_id: String,
        val mail_address_id: String,
        val mail_from: String,
        val mail_preview: String,
        val mail_text_only: String,
        val mail_text: String,
        val mail_html: String,
        val mail_timestamp: Double,
        val mail_attachments_count: Int,
        val mail_attachments: Any
    )
}
