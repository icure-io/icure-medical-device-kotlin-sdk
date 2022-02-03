package io.icure.md.client.mappers

import io.icure.kraken.client.models.PropertyTypeStubDto
import io.icure.md.client.models.PropertyType

fun PropertyTypeStubDto.toPropertyType() = PropertyType(
    identifier = this.identifier,
    type = this.type?.toPropertyTypeType(),
)

private fun PropertyTypeStubDto.Type.toPropertyTypeType() = PropertyType.Type.valueOf(this.name)
