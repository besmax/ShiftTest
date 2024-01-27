package max.bes.shifttest.users.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import max.bes.shifttest.users.domain.models.ErrorType
import max.bes.shifttest.users.domain.models.User
import max.bes.shifttest.users.domain.repositories.UserRepository
import max.bes.shifttest.util.Resource

class GetUsersUseCase(
    private val repository: UserRepository
) {
    suspend fun execute(): Flow<Resource<List<User>>> {
        val usersFromDb = repository.getFromDb()

        return if (usersFromDb.isEmpty()) {
            repository.getFromNetwork().map { networkResponse ->
                if (networkResponse is Resource.Success && !networkResponse.data.isNullOrEmpty()) {
                    repository.clearDb()
                    repository.insertListToDb(networkResponse.data)
                    Resource.Success(repository.getFromDb())
                } else {
                    Resource.Error(ErrorType.SERVER_ERROR)
                }
            }
        } else {
            flow { Resource.Success(usersFromDb) }
        }
    }
}