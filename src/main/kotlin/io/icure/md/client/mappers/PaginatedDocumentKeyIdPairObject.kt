package io.icure.md.client.mappers

import io.icure.kraken.client.models.PaginatedDocumentKeyIdPairObject
import io.icure.md.client.models.PaginatedDocumentKeyAndIdPairObject

fun PaginatedDocumentKeyIdPairObject.toPaginatedDocumentKeyAndIdPairObject(): PaginatedDocumentKeyAndIdPairObject {
    return PaginatedDocumentKeyAndIdPairObject(
        startKey = startKey,
        startKeyDocId = startKeyDocId
    )
}
