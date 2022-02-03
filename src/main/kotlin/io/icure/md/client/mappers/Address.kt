package io.icure.md.client.mappers

import io.icure.kraken.client.models.AddressDto
import io.icure.kraken.client.models.TelecomDto
import io.icure.md.client.models.Address
import io.icure.md.client.models.Telecom

fun AddressDto.toAddress() = Address(
    telecoms = this.telecoms.map { it.toTelecom() },
    addressType = this.addressType?.toAddressType(),
    description = this.descr,
    street = this.street,
    houseNumber = this.houseNumber,
    postboxNumber = this.postboxNumber,
    postalCode = this.postalCode,
    city = this.city,
    state = this.state,
    country = this.country,
    note = this.note
)

private fun AddressDto.AddressType.toAddressType() = Address.AddressType.valueOf(this.name)

private fun TelecomDto.toTelecom() = Telecom(
    telecomType = this.telecomType?.toTelecomType(),
    telecomNumber = this.telecomNumber,
    telecomDescription = this.telecomDescription,
)

private fun TelecomDto.TelecomType.toTelecomType() = Telecom.TelecomType.valueOf(this.name)

fun Address.toAddressDto() = AddressDto(
    telecoms = this.telecoms.map { it.toTelecomDto() },
    addressType = this.addressType?.toAddressTypeDto(),
    descr = this.description,
    street = this.street,
    houseNumber = this.houseNumber,
    postboxNumber = this.postboxNumber,
    postalCode = this.postalCode,
    city = this.city,
    state = this.state,
    country = this.country,
    note = this.note
)

private fun Address.AddressType.toAddressTypeDto() = AddressDto.AddressType.valueOf(this.name)

private fun Telecom.toTelecomDto() = TelecomDto(
    telecomType = this.telecomType?.toTelecomTypeDto(),
    telecomNumber = this.telecomNumber,
    telecomDescription = this.telecomDescription,
)

private fun Telecom.TelecomType.toTelecomTypeDto() = TelecomDto.TelecomType.valueOf(this.name)
