package io.icure.md.client.exceptions

class AuthenticationException(status: Int, reason: String?) :
    Exception("$status - $reason") {
}
