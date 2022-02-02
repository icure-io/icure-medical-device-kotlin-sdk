package io.icure.kraken.client.apis.impl

import io.icure.kraken.client.apis.HealthcareElementApi
import io.icure.kraken.client.models.Filter
import io.icure.kraken.client.models.HealthcareElement
import io.icure.kraken.client.models.PaginatedListHealthcareElement
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class HealthcareElementApiImpl : HealthcareElementApi {
    override suspend fun createOrModifyHealthcareElement(healthcareElement: HealthcareElement): HealthcareElement {
        TODO("Not yet implemented")
    }

    override suspend fun createOrModifyHealthcareElements(healthcareElement: List<HealthcareElement>): List<HealthcareElement> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteHealthcareElement(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun filterHealthcareElement(filter: Filter): PaginatedListHealthcareElement {
        TODO("Not yet implemented")
    }

    override suspend fun getHealthcareElement(id: String): HealthcareElement {
        TODO("Not yet implemented")
    }

    override suspend fun matchHealthcareElement(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }
}
