package io.icure.md.client.apis.impl

import io.icure.kraken.client.models.FilterChainHealthcareParty
import io.icure.kraken.client.models.ListOfIdsDto
import io.icure.md.client.apis.HealthcareProfessionalApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.mappers.toAbstractFilterDtoHealthcareParty
import io.icure.md.client.mappers.toHealthcarePartyDto
import io.icure.md.client.mappers.toHealthcareProfessional
import io.icure.md.client.mappers.toPaginatedListHealthcareProfessional
import io.icure.md.client.models.HealthcareProfessional
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class HealthcareProfessionalApiImpl(val api: MedTechApi) : HealthcareProfessionalApi {
    override suspend fun createOrModifyHealthcareProfessional(healthcareProfessional: HealthcareProfessional) =
        (healthcareProfessional.rev?.let {
            api.hcpApi().modifyHealthcareParty(healthcareProfessional.toHealthcarePartyDto())
        } ?: api.hcpApi()
            .createHealthcareParty(healthcareProfessional.toHealthcarePartyDto())).toHealthcareProfessional()

    override suspend fun deleteHealthcareProfessional(hcpId: String) =
        api.hcpApi().deleteHealthcareParties(ListOfIdsDto(ids = listOf(hcpId))).firstOrNull()?.rev
            ?: throw IllegalArgumentException("Invalid user id")

    override suspend fun filterHealthcareProfessionalBy(
        filter: Filter<HealthcareProfessional>,
        nextHcpId: String?,
        limit: Int?
    ) = api.hcpApi().filterHealthPartiesBy(
        FilterChainHealthcareParty(filter.toAbstractFilterDtoHealthcareParty(), null),
        nextHcpId,
        limit
    )
        .toPaginatedListHealthcareProfessional()

    override suspend fun getHealthcareProfessional(hcpId: String) =
        api.hcpApi().getHealthcareParty(hcpId).toHealthcareProfessional()

    override suspend fun matchHealthcareProfessionalBy(filter: Filter<HealthcareProfessional>) =
        api.hcpApi().matchHealthcarePartiesBy(filter.toAbstractFilterDtoHealthcareParty())
}
