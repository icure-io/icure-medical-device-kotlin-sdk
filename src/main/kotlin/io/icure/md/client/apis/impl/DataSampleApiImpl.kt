package io.icure.md.client.apis.impl

import io.icure.md.client.apis.DataSampleApi
import io.icure.md.client.models.DataSample
import io.icure.md.client.models.Document
import io.icure.md.client.models.Filter
import io.icure.md.client.models.PaginatedListDataSample
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

    override suspend fun deleteAttachment(dataSampleId: String, documentId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDataSample(dataSampleId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDataSamples(dataSampleIds: List<String>): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun filterDataSample(filter: Filter): PaginatedListDataSample {
        TODO("Not yet implemented")
    }

    override suspend fun getDataSample(dataSampleId: String): DataSample {
        TODO("Not yet implemented")
    }

    override suspend fun getDataSampleAttachment(dataSampleId: String, documentId: String): Document {
        TODO("Not yet implemented")
    }

    override suspend fun getDataSampleAttachmentContent(
        dataSampleId: String,
        documentId: String,
        attachmentId: String
    ): List<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun matchDataSample(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun setDataSampleAttachment(
        dataSampleId: String,
        documentId: String,
        attachment: Flow<ByteBuffer>
    ): Document {
        TODO("Not yet implemented")
    }
}
