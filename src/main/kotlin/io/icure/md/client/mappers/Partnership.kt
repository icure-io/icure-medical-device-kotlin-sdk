package io.icure.md.client.mappers

import io.icure.kraken.client.models.PartnershipDto
import io.icure.md.client.models.Partnership

fun PartnershipDto.toPartnership() = Partnership(
    type = this.type?.toPartnershipType(),
    status = this.status?.toPartnershipStatus(),
    partnerId = this.partnerId
)

private fun PartnershipDto.Status.toPartnershipStatus() = Partnership.Status.valueOf(this.name)
private fun PartnershipDto.Type.toPartnershipType() = Partnership.Type.valueOf(this.name)

fun Partnership.toPartnershipDto() = PartnershipDto(
    type = this.type?.toPartnershipType(),
    status = this.status?.toPartnershipStatus(),
    partnerId = this.partnerId
)

private fun Partnership.Status.toPartnershipStatus() = PartnershipDto.Status.valueOf(this.name)
private fun Partnership.Type.toPartnershipType() = PartnershipDto.Type.valueOf(this.name)
