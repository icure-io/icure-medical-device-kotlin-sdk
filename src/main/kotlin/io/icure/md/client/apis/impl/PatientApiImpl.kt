package io.icure.md.client.apis.impl

import io.icure.kraken.client.crypto.patientCryptoConfig
import io.icure.kraken.client.extendedapis.createPatient
import io.icure.kraken.client.extendedapis.getPatient
import io.icure.kraken.client.extendedapis.modifyPatient
import io.icure.kraken.client.models.ListOfIdsDto
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.PatientApi
import io.icure.md.client.isUUID
import io.icure.md.client.mappers.toPatient
import io.icure.md.client.mappers.toPatientDto
import io.icure.md.client.models.Filter
import io.icure.md.client.models.PaginatedListPatient
import io.icure.md.client.models.Patient
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class PatientApiImpl(private val api: MedTechApi) : PatientApi {
    override suspend fun createOrModifyPatient(patient: Patient): Patient {
        val localCrypto = api.localCrypto()
        val currentUser = api.userApi().getCurrentUser()
        val ccPatient = patientCryptoConfig(localCrypto)

        return patient.takeIf { it.rev != null }?.let {
            if (it.id == null || !it.id.isUUID()) {
                throw IllegalArgumentException("Update id should be provided as an UUID")
            }
            api.patientApi().modifyPatient(currentUser, it.toPatientDto(), ccPatient).toPatient()
        } ?: api.patientApi().createPatient(currentUser, patient.toPatientDto(), ccPatient).toPatient()
    }

    override suspend fun deletePatient(id: String): String {
        return api.patientApi().deletePatients(ListOfIdsDto(listOf(id))).singleOrNull()?.rev
            ?: throw IllegalArgumentException("Invalid user id")
    }

    override suspend fun filterPatients(filter: Filter, nextPatientId: String?, limit: Int?): PaginatedListPatient {
        TODO("Not yet implemented")
    }

    override suspend fun getPatient(id: String): Patient {
        val localCrypto = api.localCrypto()
        val currentUser = api.userApi().getCurrentUser()

        return api.patientApi().getPatient(currentUser, id, patientCryptoConfig(localCrypto)).toPatient()
    }

    override suspend fun matchPatients(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }
}
