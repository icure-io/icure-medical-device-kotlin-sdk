package io.icure.kraken.client.apis.impl

import io.icure.kraken.client.apis.DataSampleApi
import io.icure.kraken.client.models.DataSample
import io.icure.kraken.client.models.Document
import io.icure.kraken.client.models.Filter
import io.icure.kraken.client.models.PaginatedListDataSample
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import java.nio.ByteBuffer

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class DataSampleApiImpl : DataSampleApi {
    override suspend fun createOrModifyDataSample(dataSample: DataSample): DataSample {
        TODO("Not yet implemented")
    }

    override suspend fun createOrModifyDataSamples(dataSample: List<DataSample>): List<DataSample> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAttachment(id: String, documentId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDataSample(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDataSamples(ids: List<String>): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun filterDataSample(filter: Filter): PaginatedListDataSample {
        TODO("Not yet implemented")
    }

    override suspend fun getDataSample(id: String): DataSample {
        TODO("Not yet implemented")
    }

    override suspend fun getDataSampleAttachment(id: String, documentId: String): Document {
        TODO("Not yet implemented")
    }

    override suspend fun getDataSampleAttachmentContent(id: String, documentId: String, attachmentId: String): List<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun matchDataSample(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun setDataSampleAttachment(id: String, documentId: String, body: Flow<ByteBuffer>): Document {
        TODO("Not yet implemented")
    }
}
