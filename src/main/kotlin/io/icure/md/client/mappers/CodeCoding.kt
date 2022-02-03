package io.icure.md.client.mappers

import io.icure.kraken.client.models.CodeDto
import io.icure.md.client.models.Coding

fun CodeDto.toCoding() = Coding(
    id = this.id,
    qualifiedLinks = this.qualifiedLinks,
    searchTerms = this.searchTerms,
    rev = this.rev,
    type = this.type,
    code = this.code,
    version = this.version,
    description = this.label,
)

fun Coding.toCodeDto() = CodeDto(
    id = this.id,
    qualifiedLinks = this.qualifiedLinks,
    searchTerms = this.searchTerms,
    rev = this.rev,
    type = this.type,
    code = this.code,
    version = this.version,
    label = this.description,
)
