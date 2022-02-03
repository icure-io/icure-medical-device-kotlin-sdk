package io.icure.md.client.apis.impl

import io.icure.md.client.apis.CodingApi
import io.icure.md.client.models.Coding
import io.icure.md.client.models.Filter
import io.icure.md.client.models.PaginatedListCoding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class CodingApiImpl : CodingApi {
    override suspend fun createOrModifyCoding(coding: Coding): Coding {
        TODO("Not yet implemented")
    }

    override suspend fun createOrModifyCodings(coding: List<Coding>): List<Coding> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCoding(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun filterCoding(filter: Filter): PaginatedListCoding {
        TODO("Not yet implemented")
    }

    override suspend fun getCoding(id: String): Coding {
        TODO("Not yet implemented")
    }

    override suspend fun matchCoding(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }
}
