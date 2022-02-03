package io.icure.md.client.mappers

import io.icure.kraken.client.models.AbstractFilterDtoUser
import io.icure.md.client.models.Filter

fun Filter.toAbstractFilterDtoUser() = AbstractFilterDtoUser(
    desc = this.description
)
