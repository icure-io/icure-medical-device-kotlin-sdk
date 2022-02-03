package io.icure.md.client.mappers

import io.icure.kraken.client.models.CodeStubDto
import io.icure.md.client.models.CodingReference

fun CodeStubDto.toCodingReference() = CodingReference(
    id = this.id ?: "${this.type}|${this.code}|${this.version}",
    type = this.type,
    code = this.code,
    version = this.version,
)

fun CodingReference.toCodeStubDto() = CodeStubDto(
    id = this.id,
    type = this.type,
    code = this.code,
    version = this.version,
)
