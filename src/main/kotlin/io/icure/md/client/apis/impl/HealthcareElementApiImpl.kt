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

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class HealthcareElementApiImpl(private val api: MedTechApi) : HealthcareElementApi {
    override suspend fun createOrModifyHealthcareElement(
        patientId: String,
        healthcareElement: HealthcareElement
    ): HealthcareElement {
        val localCrypto = api.localCrypto()
        val currentUser = api.userApi().getCurrentUser()
        val ccHealthElement = healthElementCryptoConfig(localCrypto)

        return healthcareElement.takeIf { it.rev != null }?.let {
            if (it.id == null || !it.id.isUUID()) {
                throw IllegalArgumentException("Update id should be provided as an UUID")
            }
            api.healthElementApi().modifyHealthElement(currentUser, it.toHealthcareElementDto(), ccHealthElement)
                .toHealthcareElement()
        } ?: let {
            val patient = api.patientApi().getPatient(currentUser, patientId, patientCryptoConfig(localCrypto))
            api.healthElementApi()
                .createHealthElement(currentUser, patient, healthcareElement.toHealthcareElementDto(), ccHealthElement)
                .toHealthcareElement()
        }
    }

    override suspend fun createOrModifyHealthcareElements(
        patientId: String,
        healthcareElement: List<HealthcareElement>
    ): List<HealthcareElement> {
        val localCrypto = api.localCrypto()
        val currentUser = api.userApi().getCurrentUser()
        val ccHealthElement = healthElementCryptoConfig(localCrypto)

        val heToCreate = healthcareElement.filter { it.rev == null }
        val heToUpdate = healthcareElement - heToCreate

        if (heToUpdate.any { it.id == null || !it.id.isUUID() }) {
            throw IllegalArgumentException("Update id should be provided as an UUID")
        }

        val patient = api.patientApi().getPatient(currentUser, patientId, patientCryptoConfig(localCrypto))
        val healthcareElementsCreated = api.healthElementApi()
            .createHealthElements(currentUser, patient, heToCreate.map { it.toHealthcareElementDto() }, ccHealthElement)
        val healthcareElementsUpdated = api.healthElementApi()
            .modifyHealthElements(currentUser, heToUpdate.map { it.toHealthcareElementDto() }, ccHealthElement)

        return (healthcareElementsCreated + healthcareElementsUpdated).map { it.toHealthcareElement() }
    }

    override suspend fun deleteHealthcareElement(healthElementId: String): String {
        return api.healthElementApi().deleteHealthElements(ListOfIdsDto(listOf(healthElementId))).singleOrNull()?.rev
            ?: throw IllegalArgumentException("Invalid healthcare element id")
    }

    override suspend fun filterHealthcareElement(
        filter: Filter<HealthcareElement>,
        nextHealthElementId: String?,
        limit: Int?
    ): PaginatedListHealthcareElement {
        val currentUser = api.userApi().getCurrentUser()
        val localCrypto = api.localCrypto()

        return api.healthElementApi().filterHealthElementsBy(
            currentUser,
            FilterChain(filter.toAbstractFilterDto()),
            healthElementCryptoConfig(localCrypto),
            nextHealthElementId,
            limit
        ).toPaginatedListHealthcareElement()
    }

    override suspend fun getHealthcareElement(healthcareElementId: String): HealthcareElement {
        val currentUser = api.userApi().getCurrentUser()
        val localCrypto = api.localCrypto()

        return api.healthElementApi()
            .getHealthElement(currentUser, healthcareElementId, healthElementCryptoConfig(localCrypto))
            .toHealthcareElement()
    }

    override suspend fun matchHealthcareElement(filter: Filter<HealthcareElement>): List<String> {
        return api.healthElementApi().matchHealthElementsBy(filter.toAbstractFilterDto())
    }
}
