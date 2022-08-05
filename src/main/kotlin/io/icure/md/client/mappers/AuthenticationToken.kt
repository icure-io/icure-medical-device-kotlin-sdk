package io.icure.md.client.mappers

import io.icure.kraken.client.models.AuthenticationTokenDto
import io.icure.md.client.models.AuthenticationToken

fun AuthenticationTokenDto.toAuthenticationToken() = AuthenticationToken(
    token = this.token,
    creationTime = this.creationTime,
    validity = this.validity
)

fun AuthenticationToken.toAuthenticationTokenDto() = AuthenticationTokenDto(
    token = this.token,
    creationTime = this.creationTime,
    validity = this.validity
)
