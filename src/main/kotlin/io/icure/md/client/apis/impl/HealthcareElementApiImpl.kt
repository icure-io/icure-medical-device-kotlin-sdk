package io.icure.md.client.apis.impl

import io.icure.kraken.client.crypto.healthElementCryptoConfig
import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.extendedapis.createHealthElement
import io.icure.kraken.client.extendedapis.createHealthElements
import io.icure.kraken.client.extendedapis.filterHealthElementsBy
import io.icure.kraken.client.extendedapis.getHealthElement
import io.icure.kraken.client.extendedapis.getPatient
import io.icure.kraken.client.extendedapis.modifyHealthElement
import io.icure.kraken.client.extendedapis.modifyHealthElements
import io.icure.kraken.client.models.ListOfIdsDto
import io.icure.kraken.client.models.filter.chain.FilterChain
import io.icure.md.client.apis.HealthcareElementApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.isUUID
import io.icure.md.client.mappers.toAbstractFilterDto
import io.icure.md.client.mappers.toHealthcareElement
import io.icure.md.client.mappers.toHealthcareElementDto
import io.icure.md.client.mappers.toPaginatedListHealthcareElement
import io.icure.md.client.models.HealthcareElement
import io.icure.md.client.models.PaginatedListHealthcareElement
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
@FlowPreview
class HealthcareElementApiImpl(private val medTechApi: MedTechApi) : HealthcareElementApi {
    override suspend fun createOrModifyHealthcareElement(
        patientId: String,
        healthcareElement: HealthcareElement
    ): HealthcareElement {
        val localCrypto = medTechApi.localCrypto
        val currentUser = medTechApi.baseUserApi.getCurrentUser()
        val ccHealthElement = healthElementCryptoConfig(localCrypto)

        return healthcareElement.takeIf { it.rev != null }?.let {
            if (it.id == null || !it.id.isUUID()) {
                throw IllegalArgumentException("Update id should be provided as an UUID")
            }
            medTechApi.baseHealthElementApi.modifyHealthElement(
                currentUser,
                it.toHealthcareElementDto(),
                ccHealthElement
            )
                .toHealthcareElement()
        } ?: let {
            val patient = medTechApi.basePatientApi.getPatient(currentUser, patientId, patientCryptoConfig(localCrypto))
            medTechApi.baseHealthElementApi
                .createHealthElement(currentUser, patient, healthcareElement.toHealthcareElementDto(), ccHealthElement)
                .toHealthcareElement()
        }
    }

    override suspend fun createOrModifyHealthcareElements(
        patientId: String,
        healthcareElement: List<HealthcareElement>
    ): List<HealthcareElement> {
        val localCrypto = medTechApi.localCrypto
        val currentUser = medTechApi.baseUserApi.getCurrentUser()
        val ccHealthElement = healthElementCryptoConfig(localCrypto)

        val heToCreate = healthcareElement.filter { it.rev == null }
        val heToUpdate = healthcareElement - heToCreate

        if (heToUpdate.any { it.id == null || !it.id.isUUID() }) {
            throw IllegalArgumentException("Update id should be provided as an UUID")
        }

        val patient = medTechApi.basePatientApi.getPatient(currentUser, patientId, patientCryptoConfig(localCrypto))
        val healthcareElementsCreated = medTechApi.baseHealthElementApi
            .createHealthElements(currentUser, patient, heToCreate.map { it.toHealthcareElementDto() }, ccHealthElement)
        val healthcareElementsUpdated = medTechApi.baseHealthElementApi
            .modifyHealthElements(currentUser, heToUpdate.map { it.toHealthcareElementDto() }, ccHealthElement)

        return (healthcareElementsCreated + healthcareElementsUpdated).map { it.toHealthcareElement() }
    }

    override suspend fun deleteHealthcareElement(healthElementId: String): String {
        return medTechApi.baseHealthElementApi.deleteHealthElements(ListOfIdsDto(listOf(healthElementId)))
            .singleOrNull()?.rev
            ?: throw IllegalArgumentException("Invalid healthcare element id")
    }

    override suspend fun filterHealthcareElement(
        filter: Filter<HealthcareElement>,
        nextHealthElementId: String?,
        limit: Int?
    ): PaginatedListHealthcareElement {
        val currentUser = medTechApi.baseUserApi.getCurrentUser()
        val localCrypto = medTechApi.localCrypto

        return medTechApi.baseHealthElementApi.filterHealthElementsBy(
            currentUser,
            FilterChain(filter.toAbstractFilterDto()),
            healthElementCryptoConfig(localCrypto),
            nextHealthElementId,
            limit
        ).toPaginatedListHealthcareElement()
    }

    override suspend fun getHealthcareElement(healthcareElementId: String): HealthcareElement {
        val currentUser = medTechApi.baseUserApi.getCurrentUser()
        val localCrypto = medTechApi.localCrypto

        return medTechApi.baseHealthElementApi
            .getHealthElement(currentUser, healthcareElementId, healthElementCryptoConfig(localCrypto))
            .toHealthcareElement()
    }

    override suspend fun matchHealthcareElement(filter: Filter<HealthcareElement>): List<String> {
        return medTechApi.baseHealthElementApi.matchHealthElementsBy(filter.toAbstractFilterDto())
    }
}
