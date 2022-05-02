package io.icure.md.client.apis.e2e

import io.icure.diffutils.Diff
import io.icure.diffutils.differences
import io.icure.diffutils.filterDiffs
import io.icure.md.client.apis.CodingApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.impl.CodingApiImpl
import io.icure.md.client.models.Coding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalTime
@DisplayName("Coding tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CodingApiImplTest {

    private val iCurePath = "https://kraken.icure.dev"
    private val authHeader = TestUtils.basicAuthFrom(".credentials")
    private val healthcareProfessionalId = "782f1bcd-9f3f-408a-af1b-cd9f3f908a98"
    private val healthcareProfessionalPrivateKey =
        TestUtils.healthcareProfessionalPrivateKey(healthcareProfessionalId, this::class.java)
    private val healthcareProfessionalPublicKey =
        runBlocking { TestUtils.healthcareProfessionalPublicKey(iCurePath, authHeader, healthcareProfessionalId) }

    private val medTechApi = MedTechApi.Builder()
        .iCureUrlPath(iCurePath)
        .authorization(authHeader)
        .addKeyPair(healthcareProfessionalId, healthcareProfessionalPublicKey, healthcareProfessionalPrivateKey)
        .build()

    private val testedInstance: CodingApi = CodingApiImpl(medTechApi)

    @Test
    @DisplayName("Create coding test - HappyFlow")
    fun createDeviceHappyFlow() = runBlocking {
        val coding = coding()
        val createdCoding = testedInstance.createOrModifyCodings(listOf(coding)).single()

        val diffs = coding.differences(createdCoding)
        val filters = listOf("id", "rev")
        val filteredDiffs = filterDiffs(createdCoding, coding, diffs, filters)
        Assertions.assertEquals(emptyList<Diff>(), filteredDiffs)
    }

    @Test
    @DisplayName("Get coding test - HappyFlow")
    fun getDeviceHappyFlow() = runBlocking {
        val coding = coding()
        val createdCoding = testedInstance.createOrModifyCodings(listOf(coding)).single()
        val gotCoding = testedInstance.getCoding(createdCoding.id!!)

        val diffs = createdCoding.differences(gotCoding)
        Assertions.assertEquals(emptyList<Diff>(), diffs)
    }

    private fun coding() = Coding(
        description = mapOf("fr" to "Bonjour le monde", "en" to "Hello world"),
        code = "${UUID.randomUUID()}",
        type = "TEST",
        version = "1"
    )
}
