package io.icure.md.client.apis.impl

import io.icure.kraken.client.models.FilterChainUser
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.UserApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.mappers.toAbstractFilterDtoUser
import io.icure.md.client.mappers.toPaginatedListUser
import io.icure.md.client.mappers.toUser
import io.icure.md.client.mappers.toUserDto
import io.icure.md.client.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class UserApiImpl(val api: MedTechApi) : UserApi {
    override suspend fun checkTokenValidity(userId: String, token: String) =
        api.userApi().checkTokenValidity(userId, token)

    override suspend fun createOrModifyUser(user: User) =
        (user.rev?.let { api.userApi().modifyUser(user.toUserDto()) } ?: api.userApi()
            .createUser(user.toUserDto())).toUser()

    override suspend fun createToken(userId: String) =
        api.userApi().getToken(userId, UUID.randomUUID().toString(), 3600 * 24 * 30)

    override suspend fun deleteUser(userId: String) =
        api.userApi().deleteUser(userId).rev ?: throw IllegalArgumentException("Invalid user id")

    override suspend fun filterUsers(filter: Filter<User>, nextUserId: String?, limit: Int?) =
        api.userApi().filterUsersBy(FilterChainUser(filter.toAbstractFilterDtoUser(), null), nextUserId, limit)
            .toPaginatedListUser()

    override suspend fun getLoggedUser() = api.userApi().getCurrentUser().toUser()

    override suspend fun getUser(userId: String) = api.userApi().getUser(userId).toUser()

    override suspend fun matchUsers(filter: Filter<User>) = api.userApi().matchUsersBy(filter.toAbstractFilterDtoUser())
}
