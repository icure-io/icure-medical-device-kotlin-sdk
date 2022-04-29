package io.icure.md.client.models

import io.icure.kraken.client.models.UserDto

data class ApiInitialisationResult(
    val userDto: UserDto,
    val token: String,
    val keyPair: Pair<String, String>?
)
