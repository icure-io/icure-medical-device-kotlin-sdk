package io.icure.md.client.apis.impl

import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.extendedapis.createPatient
import io.icure.kraken.client.extendedapis.filterPatientsBy
import io.icure.kraken.client.extendedapis.getPatient
import io.icure.kraken.client.extendedapis.modifyPatient
import io.icure.kraken.client.models.ListOfIdsDto
import io.icure.kraken.client.models.filter.chain.FilterChain
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.PatientApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.isUUID
import io.icure.md.client.mappers.dataOwnerId
import io.icure.md.client.mappers.toAbstractFilterDto
import io.icure.md.client.mappers.toPaginatedListPatient
import io.icure.md.client.mappers.toPatient
import io.icure.md.client.mappers.toPatientDto
import io.icure.md.client.models.Delegation
import io.icure.md.client.models.PaginatedListPatient
import io.icure.md.client.models.Patient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@FlowPreview
@ExperimentalTime
class PatientApiImpl(private val medTechApi: MedTechApi) : PatientApi {
    override suspend fun createOrModifyPatient(patient: Patient): Patient {
        val localCrypto = medTechApi.localCrypto
        val currentUser = medTechApi.baseUserApi.getCurrentUser()
        val ccPatient = patientCryptoConfig(localCrypto)

        return patient.takeIf { it.rev != null }?.let {
            if (it.id == null || !it.id.isUUID()) {
                throw IllegalArgumentException("Update id should be provided as an UUID")
            }
            medTechApi.basePatientApi.modifyPatient(currentUser, it.toPatientDto(), ccPatient).toPatient()
        } ?: medTechApi.basePatientApi.createPatient(currentUser, patient.toPatientDto(), ccPatient).toPatient()
    }

    override suspend fun deletePatient(patientId: String): String {
        return medTechApi.basePatientApi.deletePatients(ListOfIdsDto(listOf(patientId))).singleOrNull()?.rev
            ?: throw IllegalArgumentException("Invalid user id")
    }

    override suspend fun filterPatients(
        filter: Filter<Patient>,
        nextPatientId: String?,
        limit: Int?
    ): PaginatedListPatient {
        val currentUser = medTechApi.baseUserApi.getCurrentUser()
        val localCrypto = medTechApi.localCrypto

        return medTechApi.basePatientApi
            .filterPatientsBy(
                currentUser,
                FilterChain(filter.toAbstractFilterDto(), null),
                null,
                nextPatientId,
                limit,
                null, null, null,
                patientCryptoConfig(localCrypto)
            )
            .toPaginatedListPatient()
    }

    override suspend fun getPatient(patientId: String): Patient {
        val localCrypto = medTechApi.localCrypto
        val currentUser = medTechApi.baseUserApi.getCurrentUser()

        return medTechApi.basePatientApi.getPatient(currentUser, patientId, patientCryptoConfig(localCrypto))
            .toPatient()
    }

    override suspend fun matchPatients(filter: Filter<Patient>): List<String> {
        return medTechApi.basePatientApi.matchPatientsBy(filter.toAbstractFilterDto())
    }

    override suspend fun giveAccessTo(patient: Patient, delegateTo: String): Patient {
        val localCrypto = medTechApi.localCrypto
        val currentUser = medTechApi.baseUserApi.getCurrentUser()
        val dataOwnerId = currentUser.dataOwnerId()

        if (!patient.systemMetaData!!.delegations.keys.any { it == dataOwnerId }) {
            throw IllegalStateException("DataOwner $dataOwnerId does not have the right to access patient ${patient.id}")
        }

        if (patient.systemMetaData.delegations.keys.any { it == delegateTo }) {
            return patient
        }

        return patient.toPatientDto().let { patientDto ->
            val (delegation_Key, dataOwner) = localCrypto.encryptAESKeyForDataOwner(
                dataOwnerId,
                delegateTo,
                patientDto.id,
                localCrypto.decryptEncryptionKeys(dataOwnerId, patientDto.delegations).first()
            )
            val (encryptionKey_Key, _) = localCrypto.encryptAESKeyForDataOwner(
                dataOwnerId,
                delegateTo,
                patientDto.id,
                localCrypto.decryptEncryptionKeys(dataOwnerId, patientDto.encryptionKeys).first()
            )

            val delegation = Delegation(owner = dataOwnerId, delegatedTo = delegateTo, key = delegation_Key)
            val encryptionKey = Delegation(owner = dataOwnerId, delegatedTo = delegateTo, key = encryptionKey_Key)

            val delegations = patient.systemMetaData.delegations.plus(delegateTo to setOf(delegation))
            val encryptionKeys = patient.systemMetaData.encryptionKeys.plus(delegateTo to setOf(encryptionKey))

            val patientToUpdate = if (dataOwner != null && dataOwner.dataOwnerId == patientDto.id) {
                patient.copy(
                    rev = dataOwner.rev,
                    systemMetaData = patient.systemMetaData.copy(
                        hcPartyKeys = dataOwner.hcPartyKeys,
                        delegations = delegations,
                        encryptionKeys = encryptionKeys
                    )
                )
            } else {
                patient.copy(
                    systemMetaData = patient.systemMetaData.copy(
                        delegations = delegations,
                        encryptionKeys = encryptionKeys
                    )
                )
            }

            createOrModifyPatient(patientToUpdate)
        }
    }
}
