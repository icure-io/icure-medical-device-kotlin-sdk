package io.icure.kraken.client.apis.impl

import io.icure.kraken.client.apis.CodingApi
import io.icure.kraken.client.models.Coding
import io.icure.kraken.client.models.Filter
import io.icure.kraken.client.models.PaginatedListCoding
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
