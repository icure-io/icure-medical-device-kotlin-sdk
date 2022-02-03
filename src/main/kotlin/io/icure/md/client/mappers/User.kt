package io.icure.md.client.mappers

import io.icure.kraken.client.models.UserDto
import io.icure.md.client.models.User

fun UserDto.toUser() = User(
    id = this.id,
            properties = this.properties.map { it.toProperty() },
            roles = this.roles,
            autoDelegations = this.autoDelegations,
            authenticationTokens = this.authenticationTokens.mapValues { (k,v) -> v.toAuthenticationToken() },
            rev = this.rev,
            deletionDate = this.deletionDate,
            created = this.created,
            name = this.name,
            login = this.login,
            passwordHash = this.passwordHash,
            secret = this.secret,
            use2fa = this.use2fa,
            groupId = this.groupId,
            healthcarePartyId = this.healthcarePartyId,
            patientId = this.patientId,
            deviceId = this.deviceId,
            email = this.email,
            mobilePhone = this.mobilePhone,
)
