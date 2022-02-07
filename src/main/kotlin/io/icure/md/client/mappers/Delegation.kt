package io.icure.md.client.mappers

import io.icure.kraken.client.models.DelegationDto
import io.icure.md.client.models.Delegation

fun Delegation.toDelegationDto() = DelegationDto(this.tags, this.owner, this.delegatedTo, this.key)
fun DelegationDto.toDelegation() = Delegation(this.tags, this.owner, this.delegatedTo, this.key)
