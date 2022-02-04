package io.icure.md.client.apis.impl

import io.icure.md.client.apis.CodingApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.isUUID
import io.icure.md.client.mappers.toCodeDto
import io.icure.md.client.mappers.toCoding
import io.icure.md.client.models.Coding
import io.icure.md.client.models.Filter
import io.icure.md.client.models.PaginatedListCoding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class CodingApiImpl(private val api: MedTechApi) : CodingApi {
    override suspend fun createOrModifyCoding(coding: Coding): Coding {
        return coding.takeIf { it.rev != null }?.let {
            if (it.id == null || !it.id.isUUID()) {
                throw IllegalArgumentException("Update id should be provided as an UUID")
            }
            api.codeApi().modifyCode(it.toCodeDto()).toCoding()
        } ?: api.codeApi().createCode(coding.toCodeDto()).toCoding()
    }

    override suspend fun createOrModifyCodings(coding: List<Coding>): List<Coding> {
        val codingsToCreate = coding.filter { it.rev == null }
        val codingsToUpdate = coding - codingsToCreate

        if (codingsToUpdate.any { it.id == null || !it.id.isUUID() }) {
            throw IllegalArgumentException("Update id should be provided as an UUID")
        }

        val createdCodeDtos = codingsToCreate.map { c -> c.toCodeDto() }.map { api.codeApi().createCode(it) }
        val updatedCodeDtos = codingsToUpdate.map { c -> c.toCodeDto() }.map { api.codeApi().modifyCode(it) }

        return (createdCodeDtos + updatedCodeDtos).map { it.toCoding() }
    }

    override suspend fun filterCoding(filter: Filter, nextCodingId: String?, limit: Int?): PaginatedListCoding {
        TODO("Not yet implemented")
    }

    override suspend fun getCoding(id: String): Coding {
        return api.codeApi().getCode(id).toCoding()
    }

    override suspend fun matchCoding(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }
}
