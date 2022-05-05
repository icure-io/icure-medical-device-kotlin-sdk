package io.icure.md.client.apis.impl

import io.icure.kraken.client.models.filter.chain.FilterChain
import io.icure.md.client.apis.CodingApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.isUUID
import io.icure.md.client.mappers.toAbstractFilterDto
import io.icure.md.client.mappers.toCodeDto
import io.icure.md.client.mappers.toCoding
import io.icure.md.client.mappers.toPaginatedListCoding
import io.icure.md.client.models.Coding
import io.icure.md.client.models.PaginatedListCoding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
@FlowPreview
class CodingApiImpl(private val medTechApi: MedTechApi) : CodingApi {
    override suspend fun createOrModifyCoding(coding: Coding): Coding {
        return coding.takeIf { it.rev != null }?.let {
            if (it.id == null || !it.id.isUUID()) {
                throw IllegalArgumentException("Update id should be provided as an UUID")
            }
            medTechApi.baseCodeApi.modifyCode(it.toCodeDto()).toCoding()
        } ?: medTechApi.baseCodeApi.createCode(coding.toCodeDto()).toCoding()
    }

    override suspend fun createOrModifyCodings(coding: List<Coding>): List<Coding> {
        val codingsToCreate = coding.filter { it.rev == null }
        val codingsToUpdate = coding - codingsToCreate

        if (codingsToUpdate.any { it.id == null || !it.id.isUUID() }) {
            throw IllegalArgumentException("Update id should be provided as an UUID")
        }

        val createdCodeDtos = codingsToCreate.map { c -> c.toCodeDto() }.map { medTechApi.baseCodeApi.createCode(it) }
        val updatedCodeDtos = codingsToUpdate.map { c -> c.toCodeDto() }.map { medTechApi.baseCodeApi.modifyCode(it) }

        return (createdCodeDtos + updatedCodeDtos).map { it.toCoding() }
    }

    override suspend fun filterCoding(filter: Filter<Coding>, nextCodingId: String?, limit: Int?): PaginatedListCoding {
        return medTechApi.baseCodeApi
            .filterCodesBy(null, nextCodingId, limit, null, null, null, FilterChain(filter.toAbstractFilterDto(), null))
            .toPaginatedListCoding()
    }

    override suspend fun getCoding(codingId: String): Coding {
        return medTechApi.baseCodeApi.getCode(codingId).toCoding()
    }

    override suspend fun matchCoding(filter: Filter<Coding>): List<String> {
        return medTechApi.baseCodeApi.matchCodesBy(filter.toAbstractFilterDto())
    }
}
