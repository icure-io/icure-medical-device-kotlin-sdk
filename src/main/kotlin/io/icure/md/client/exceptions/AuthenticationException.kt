package io.icure.md.client.exceptions

class AuthenticationException(status: Int, reason: String?, override val message: String? = null) :
    Exception(message = message ?: "$status - $reason : $message") {
}
