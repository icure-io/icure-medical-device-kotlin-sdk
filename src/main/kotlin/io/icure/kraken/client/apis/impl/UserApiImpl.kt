package io.icure.kraken.client.apis.impl

import io.icure.kraken.client.apis.UserApi
import io.icure.kraken.client.models.Filter
import io.icure.kraken.client.models.PaginatedListUser
import io.icure.kraken.client.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class UserApiImpl : UserApi {
    override suspend fun checkTokenValidity(xIcureToken: String, id: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun createOrModifyUser(user: User): User {
        TODO("Not yet implemented")
    }

    override suspend fun createToken(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun filterUser(filter: Filter): PaginatedListUser {
        TODO("Not yet implemented")
    }

    override suspend fun getLoggedUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun matchUser(filter: Filter): List<String> {
        TODO("Not yet implemented")
    }
}
