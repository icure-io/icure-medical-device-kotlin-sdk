package io.icure.md.client.mappers

import io.icure.kraken.client.models.AbstractFilterDtoDevice
import io.icure.kraken.client.models.AbstractFilterDtoHealthcareParty
import io.icure.kraken.client.models.AbstractFilterDtoUser
import io.icure.md.client.filter.Filter

fun Filter<*>.toAbstractFilterDtoUser() = AbstractFilterDtoUser(
    desc = this.description
)

fun Filter<*>.toAbstractFilterDtoHealthcareParty() = AbstractFilterDtoHealthcareParty(
    desc = this.description
)

fun Filter<*>.toAbstractFilterDtoDevice() = AbstractFilterDtoDevice(
    desc = this.description
)
