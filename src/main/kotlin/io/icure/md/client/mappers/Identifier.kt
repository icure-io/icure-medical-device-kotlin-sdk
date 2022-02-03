package io.icure.md.client.mappers

import io.icure.kraken.client.models.IdentifierDto
import io.icure.md.client.models.Identifier

fun IdentifierDto.toIdentifier() = Identifier(
    id = this.id,
    assigner = this.assigner,
    start = this.start,
    end = this.end,
    system = this.system,
    type = this.type?.toCodingReference(),
    use = this.use,
)

fun Identifier.toIdentifierDto() = IdentifierDto(
    id = this.id,
    assigner = this.assigner,
    start = this.start,
    end = this.end,
    system = this.system,
    type = this.type?.toCodeStubDto(),
    use = this.use,
)
