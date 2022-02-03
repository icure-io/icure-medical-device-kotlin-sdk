package io.icure.md.client.mappers

import io.icure.kraken.client.models.TypedValueDtoObject
import io.icure.md.client.models.TypedValueObject

fun TypedValueDtoObject.toTypedValueObject() = TypedValueObject(
    type = this.type?.toTypedValueObjectType(),
    booleanValue = this.booleanValue,
    integerValue = this.integerValue,
    doubleValue = this.doubleValue,
    stringValue = this.stringValue,
    dateValue = this.dateValue,
)

private fun TypedValueDtoObject.Type.toTypedValueObjectType() = TypedValueObject.Type.valueOf(this.name)
