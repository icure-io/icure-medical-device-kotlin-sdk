package io.icure.kraken.client.apis.impl

import io.icure.kraken.client.apis.HealthcareProfessionalApi
import io.icure.kraken.client.models.Filter
import io.icure.kraken.client.models.HealthcareProfessional
import io.icure.kraken.client.models.PaginatedListHealthcareProfessional
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class HealthcareProfessionalApiImpl : HealthcareProfessionalApi {
    override suspend fun createOrModifyHealthcareProfessional(healthcareProfessional: HealthcareProfessional): HealthcareProfessional {
        TODO("Not yet implemented")
    }

    override suspend fun deleteHealthcareProfessional(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun filterHealthcareProfessionalBy(filter: Filter): PaginatedListHealthcareProfessional {
        TODO("Not yet implemented")
    }

    override suspend fun getHealthcareProfessional(id: String): HealthcareProfessional {
        TODO("Not yet implemented")
    }

    override suspend fun matchHealthcareProfessionalBy(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }
}
