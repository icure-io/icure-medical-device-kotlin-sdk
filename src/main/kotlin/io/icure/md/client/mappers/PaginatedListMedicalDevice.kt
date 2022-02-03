package io.icure.md.client.mappers

import io.icure.kraken.client.models.PaginatedListDeviceDto
import io.icure.md.client.models.PaginatedListMedicalDevice

fun PaginatedListDeviceDto.toPaginatedListMedicalDevice(): PaginatedListMedicalDevice {
    return PaginatedListMedicalDevice(
        pageSize = this.pageSize,
        totalSize = this.totalSize,
        rows = this.rows.map { it.toMedicalDevice() },
        nextKeyPair = this.nextKeyPair?.toPaginatedDocumentKeyAndIdPairObject()
    )
}
