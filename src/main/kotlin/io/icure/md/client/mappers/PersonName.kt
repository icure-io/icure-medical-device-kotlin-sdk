package io.icure.md.client.mappers

import io.icure.kraken.client.models.PersonNameDto
import io.icure.md.client.models.PersonName

fun PersonNameDto.toPersonName() = PersonName(
    firstNames = this.firstNames,
    prefix = this.prefix,
    suffix = this.suffix,
    lastName = this.lastName,
    start = this.start,
    end = this.end,
    text = this.text,
    use = this.use?.toPersonNameUse(),
)

private fun PersonNameDto.Use.toPersonNameUse() = PersonName.Use.valueOf(this.name)

fun PersonName.toPersonNameDto() = PersonNameDto(
    firstNames = this.firstNames,
    prefix = this.prefix,
    suffix = this.suffix,
    lastName = this.lastName,
    start = this.start,
    end = this.end,
    text = this.text,
    use = this.use?.toPersonNameDtoUse(),
)

private fun PersonName.Use.toPersonNameDtoUse() = PersonNameDto.Use.valueOf(this.name)
