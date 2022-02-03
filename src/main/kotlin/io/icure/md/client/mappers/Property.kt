package io.icure.md.client.mappers

import io.icure.kraken.client.models.PropertyStubDto
import io.icure.md.client.models.Property

fun PropertyStubDto.toProperty() = Property(
    id = this.id,
    type = this.type?.toPropertyType(),
    typedValue = this.typedValue?.toTypedValueObject(),
    deleted = this.deletionDate,
)
