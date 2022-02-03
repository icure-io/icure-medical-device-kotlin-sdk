package io.icure.md.client.apis.impl

import io.icure.md.client.apis.UserApi
import io.icure.md.client.mappers.toUser
import io.icure.md.client.mappers.toUserDto
import io.icure.md.client.models.Filter
import io.icure.md.client.models.PaginatedListUser
import io.icure.md.client.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class UserApiImpl(val api: io.icure.kraken.client.apis.UserApi) : UserApi {
    override suspend fun checkTokenValidity(userId: String, token: String) = api.checkTokenValidity(userId, token)

    override suspend fun createOrModifyUser(user: User) = (user.rev?.let { api.modifyUser(user.toUserDto()) } ?: api.createUser(user.toUserDto())).toUser()

    override suspend fun createToken(userId: String) = api.getToken(userId, UUID.randomUUID().toString(), 3600*24*30)

    override suspend fun deleteUser(userId: String) = api.deleteUser(userId).rev ?: throw IllegalArgumentException("Invalid user id")

    override suspend fun filterUser(filter: Filter): PaginatedListUser {
        TODO("Not yet implemented")
    }

    override suspend fun getLoggedUser() = api.getCurrentUser().toUser()

    override suspend fun getUser(userId: String) = api.getUser(userId).toUser()

    override suspend fun matchUser(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }
}
