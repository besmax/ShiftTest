package max.bes.shifttest.users.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
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
    suspend fun execute(dispatcher: CoroutineDispatcher): Flow<Resource<List<User>>> {
        val usersFromDb = repository.getFromDb(dispatcher)

        return if (usersFromDb.isEmpty()) {
            repository.getFromNetwork(dispatcher).map { networkResponse ->
                if (networkResponse is Resource.Success && !networkResponse.data.isNullOrEmpty()) {
                    repository.clearDb(dispatcher)
                    repository.insertListToDb(networkResponse.data, dispatcher)
                    Resource.Success(repository.getFromDb(dispatcher))
                } else {
                    networkResponse
                }
            }
        } else {
            flow { emit(Resource.Success(usersFromDb)) }
        }
    }
}