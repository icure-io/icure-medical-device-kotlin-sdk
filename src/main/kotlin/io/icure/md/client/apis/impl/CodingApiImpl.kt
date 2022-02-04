package io.icure.md.client.apis.impl

import io.icure.md.client.apis.CodingApi
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.models.Coding
import io.icure.md.client.models.Filter
import io.icure.md.client.models.PaginatedListCoding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class CodingApiImpl(private val api: MedTechApi) : CodingApi {
    override suspend fun createOrModifyCoding(coding: Coding): Coding {
        TODO("Not yet implemented")
    }

    override suspend fun createOrModifyCodings(coding: List<Coding>): List<Coding> {
        TODO("Not yet implemented")
    }

    override suspend fun filterCoding(filter: Filter, nextCodingId: String?, limit: Int?): PaginatedListCoding {
        TODO("Not yet implemented")
    }

    override suspend fun getCoding(id: String): Coding {
        TODO("Not yet implemented")
    }

    override suspend fun matchCoding(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }
}
