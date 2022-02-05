package io.icure.md.client.apis.impl

import io.icure.md.client.apis.HealthcareElementApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.models.HealthcareElement
import io.icure.md.client.models.PaginatedListHealthcareElement
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

    override suspend fun filterHealthcareElement(filter: Filter<HealthcareElement>): PaginatedListHealthcareElement {
        TODO("Not yet implemented")
    }

    override suspend fun getHealthcareElement(id: String): HealthcareElement {
        TODO("Not yet implemented")
    }

    override suspend fun matchHealthcareElement(filter: Filter<HealthcareElement>): List<String> {
        TODO("Not yet implemented")
    }
}
