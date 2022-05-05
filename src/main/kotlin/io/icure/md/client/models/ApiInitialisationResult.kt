package io.icure.md.client.models

import io.icure.kraken.client.models.UserDto
import java.security.KeyPair

data class ApiInitialisationResult(
    val userDto: UserDto,
    val token: String,
    val keyPair: KeyPair?
)
