package io.icure.md.client.apis.impl

import io.icure.md.client.apis.PatientApi
import io.icure.md.client.models.Filter
import io.icure.md.client.models.PaginatedListPatient
import io.icure.md.client.models.Patient
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class PatientApiImpl : PatientApi {
    override suspend fun createOrModifyPatient(patient: Patient): Patient {
        TODO("Not yet implemented")
    }

    override suspend fun deletePatient(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun filterPatients(filter: Filter): PaginatedListPatient {
        TODO("Not yet implemented")
    }

    override suspend fun getPatient(id: String): Patient {
        TODO("Not yet implemented")
    }

    override suspend fun matchPatients(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }
}
