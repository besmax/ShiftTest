package max.bes.shifttest.users.domain.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import max.bes.shifttest.users.domain.models.User
import max.bes.shifttest.util.Resource

interface UserRepository {
    suspend fun getFromNetwork(dispatcher: CoroutineDispatcher): Flow<Resource<List<User>>>

    suspend fun insertListToDb(users: List<User>, dispatcher: CoroutineDispatcher)

    suspend fun getFromDb(dispatcher: CoroutineDispatcher): List<User>

    suspend fun clearDb(dispatcher: CoroutineDispatcher)
}