package io.icure.md.client.apis.impl

import io.icure.kraken.client.models.ListOfIdsDto
import io.icure.kraken.client.models.filter.chain.FilterChain
import io.icure.md.client.apis.HealthcareProfessionalApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.mappers.toAbstractFilterDto
import io.icure.md.client.mappers.toHealthcarePartyDto
import io.icure.md.client.mappers.toHealthcareProfessional
import io.icure.md.client.mappers.toPaginatedListHealthcareProfessional
import io.icure.md.client.models.HealthcareProfessional
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalUnsignedTypes
@FlowPreview
class HealthcareProfessionalApiImpl(private val medTechApi: MedTechApi) : HealthcareProfessionalApi {
    override suspend fun createOrModifyHealthcareProfessional(healthcareProfessional: HealthcareProfessional) =
        (healthcareProfessional.rev?.let {
            medTechApi.baseHcpApi.modifyHealthcareParty(healthcareProfessional.toHealthcarePartyDto())
        } ?: medTechApi.baseHcpApi
            .createHealthcareParty(healthcareProfessional.toHealthcarePartyDto())).toHealthcareProfessional()

    override suspend fun deleteHealthcareProfessional(hcpId: String) =
        medTechApi.baseHcpApi.deleteHealthcareParties(ListOfIdsDto(ids = listOf(hcpId))).firstOrNull()?.rev
            ?: throw IllegalArgumentException("Invalid user id")

    override suspend fun filterHealthcareProfessionalBy(
        filter: Filter<HealthcareProfessional>,
        nextHcpId: String?,
        limit: Int?
    ) = medTechApi.baseHcpApi.filterHealthPartiesBy(
        FilterChain(filter.toAbstractFilterDto(), null),
        nextHcpId,
        limit
    )
        .toPaginatedListHealthcareProfessional()

    override suspend fun getHealthcareProfessional(hcpId: String) =
        medTechApi.baseHcpApi.getHealthcareParty(hcpId).toHealthcareProfessional()

    override suspend fun matchHealthcareProfessionalBy(filter: Filter<HealthcareProfessional>) =
        medTechApi.baseHcpApi.matchHealthcarePartiesBy(filter.toAbstractFilterDto())
}
