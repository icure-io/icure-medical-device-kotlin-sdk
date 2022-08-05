/* ktlint-disable filename */
package io.icure.md.client.mappers

import io.icure.kraken.client.models.TypedValueDtoObject
import io.icure.md.client.models.TypedValueObject

fun TypedValueDtoObject.toTypedValueObject() = TypedValueObject(
    type = this.type?.toTypedValueObjectType(),
    booleanValue = this.booleanValue,
    integerValue = this.integerValue,
    doubleValue = this.doubleValue,
    stringValue = this.stringValue,
    dateValue = this.dateValue
)

private fun TypedValueDtoObject.Type.toTypedValueObjectType() = TypedValueObject.Type.valueOf(this.name)

fun TypedValueObject.toTypedValueObject() = TypedValueDtoObject(
    type = this.type?.toTypedValueDtoObjectType(),
    booleanValue = this.booleanValue,
    integerValue = this.integerValue,
    doubleValue = this.doubleValue,
    stringValue = this.stringValue,
    dateValue = this.dateValue
)

private fun TypedValueObject.Type.toTypedValueDtoObjectType() = TypedValueDtoObject.Type.valueOf(this.name)
/* ktlint-enable filename */
