package max.bes.shifttest.users.domain.repositories

import kotlinx.coroutines.flow.Flow
import max.bes.shifttest.users.domain.models.User
import max.bes.shifttest.util.Resource

interface UserRepository {
    suspend fun getFromNetwork(): Flow<Resource<List<User>>>

    suspend fun insertListToDb(users: List<User>)

    suspend fun getFromDb(): List<User>

    suspend fun clearDb()
}