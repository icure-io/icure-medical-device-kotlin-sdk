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
import io.icure.kraken.client.models.DelegationDto
import io.icure.kraken.client.models.ListOfIdsDto
import io.icure.kraken.client.models.filter.chain.FilterChain
import io.icure.md.client.apis.HealthcareElementApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.isUUID
import io.icure.md.client.mappers.dataOwnerId
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

    override suspend fun giveAccessTo(healthcareElement: HealthcareElement, delegateTo: String): HealthcareElement {
        val localCrypto = medTechApi.localCrypto
        val currentUser = medTechApi.baseUserApi.getCurrentUser()
        val dataOwnerId = currentUser.dataOwnerId()

        if (!healthcareElement.systemMetaData!!.delegations.keys.any { it == dataOwnerId }) {
            throw IllegalStateException("DataOwner $dataOwnerId does not have the right to access it ${healthcareElement.id}")
        }

        if (healthcareElement.systemMetaData.delegations.keys.any { it == delegateTo }) {
            return healthcareElement
        }

        val ccHealthElement = healthElementCryptoConfig(localCrypto)

        return healthcareElement.toHealthcareElementDto().let {
            val (patientIdKey, _) = localCrypto.encryptAESKeyForDataOwner(
                dataOwnerId,
                delegateTo,
                it.id,
                localCrypto.decryptEncryptionKeys(dataOwnerId, it.cryptedForeignKeys).first()
            )
            val (secretForeignKey, _) = localCrypto.encryptAESKeyForDataOwner(
                dataOwnerId,
                delegateTo,
                it.id,
                localCrypto.decryptEncryptionKeys(dataOwnerId, it.delegations).first()
            )
            val (encryptionKey, _) = localCrypto.encryptAESKeyForDataOwner(
                dataOwnerId,
                delegateTo,
                it.id,
                localCrypto.decryptEncryptionKeys(dataOwnerId, it.encryptionKeys).first()
            )

            val delegation = DelegationDto(owner = dataOwnerId, delegatedTo = delegateTo, key = secretForeignKey)
            val encryptionKeyDelegation =
                DelegationDto(owner = dataOwnerId, delegatedTo = delegateTo, key = encryptionKey)
            val cryptedForeignKeyDelegation =
                DelegationDto(owner = dataOwnerId, delegatedTo = delegateTo, key = patientIdKey)

            val delegations = it.delegations.plus(delegateTo to setOf(delegation))
            val encryptionKeys = it.encryptionKeys.plus(delegateTo to setOf(encryptionKeyDelegation))
            val cryptedForeignKeys = it.cryptedForeignKeys.plus(delegateTo to setOf(cryptedForeignKeyDelegation))

            val healthElementDtoToUpdate = it.copy(
                delegations = delegations,
                encryptionKeys = encryptionKeys,
                cryptedForeignKeys = cryptedForeignKeys
            )

            try {
                medTechApi.baseHealthElementApi.modifyHealthElement(
                    currentUser,
                    healthElementDtoToUpdate,
                    ccHealthElement
                ).toHealthcareElement()
            } catch (e: Exception) {
                e.printStackTrace()
                throw IllegalStateException("Couldn't give access to dataSample")
            }
        }
    }
}
