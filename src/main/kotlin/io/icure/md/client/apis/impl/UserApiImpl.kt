package io.icure.md.client.apis.impl

import io.icure.kraken.client.models.filter.chain.FilterChain
import io.icure.md.client.apis.MedTechApi
import io.icure.md.client.apis.UserApi
import io.icure.md.client.filter.Filter
import io.icure.md.client.mappers.toAbstractFilterDto
import io.icure.md.client.mappers.toPaginatedListUser
import io.icure.md.client.mappers.toUser
import io.icure.md.client.mappers.toUserDto
import io.icure.md.client.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalUnsignedTypes
@FlowPreview
class UserApiImpl(private val medTechApi: MedTechApi) : UserApi {
    override suspend fun checkTokenValidity(userId: String, token: String) =
        medTechApi.baseUserApi.checkTokenValidity(userId, token)

    override suspend fun createOrModifyUser(user: User) =
        (user.rev?.let { medTechApi.baseUserApi.modifyUser(user.toUserDto()) } ?: medTechApi.baseUserApi
            .createUser(user.toUserDto())).toUser()

    override suspend fun createToken(userId: String) =
        medTechApi.baseUserApi.getToken(userId, UUID.randomUUID().toString(), 3600 * 24 * 30)

    override suspend fun createToken(userId: String, validity: Duration): String {
        return medTechApi.baseUserApi.getToken(
            userId,
            UUID.randomUUID().toString(),
            validity.toLong(DurationUnit.SECONDS)
        )
    }

    override suspend fun deleteUser(userId: String) =
        medTechApi.baseUserApi.deleteUser(userId).rev ?: throw IllegalArgumentException("Invalid user id")

    override suspend fun filterUsers(filter: Filter<User>, nextUserId: String?, limit: Int?) =
        medTechApi.baseUserApi.filterUsersBy(FilterChain(filter.toAbstractFilterDto(), null), nextUserId, limit)
            .toPaginatedListUser()

    override suspend fun getLoggedUser() = medTechApi.baseUserApi.getCurrentUser().toUser()

    override suspend fun getUser(userId: String) = medTechApi.baseUserApi.getUser(userId).toUser()
    override suspend fun getUserByEmail(email: String): User = medTechApi.baseUserApi.getUserByEmail(email).toUser()

    override suspend fun matchUsers(filter: Filter<User>) =
        medTechApi.baseUserApi.matchUsersBy(filter.toAbstractFilterDto())
}
