package max.bes.shifttest.users.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import max.bes.shifttest.core.data.dto.requests.UsersRequest
import max.bes.shifttest.core.data.dto.responses.UsersResponse
import max.bes.shifttest.core.data.network.NetworkClient
import max.bes.shifttest.core.data.network.RetrofitNetworkClient.Companion.CODE_NO_INTERNET
import max.bes.shifttest.core.data.network.RetrofitNetworkClient.Companion.CODE_SERVER_ERROR
import max.bes.shifttest.core.data.network.RetrofitNetworkClient.Companion.CODE_SUCCESS
import max.bes.shifttest.users.data.db.dao.UserDao
import max.bes.shifttest.users.data.mappers.map
import max.bes.shifttest.users.domain.mappers.map
import max.bes.shifttest.users.domain.models.ErrorType
import max.bes.shifttest.users.domain.models.User
import max.bes.shifttest.users.domain.repositories.UserRepository
import max.bes.shifttest.util.Resource

class UserRepositoryImpl(
    private val networkClient: NetworkClient,
    private val dao: UserDao
) : UserRepository {
    override suspend fun getFromNetwork(dispatcher: CoroutineDispatcher): Flow<Resource<List<User>>> =
        flow {
            val response = networkClient.doRequest(UsersRequest())
            when (response.resultCode) {
                CODE_NO_INTERNET -> emit(Resource.Error(ErrorType.NO_INTERNET))
                CODE_SERVER_ERROR -> emit(Resource.Error(ErrorType.SERVER_ERROR))
                CODE_SUCCESS -> emit(Resource.Success(
                    data = (response as UsersResponse).results.map { it.map() }
                ))
            }
        }.flowOn(dispatcher)

    override suspend fun insertListToDb(users: List<User>, dispatcher: CoroutineDispatcher) {
        withContext(dispatcher) {
            val entites = users.map { it.map() }
            dao.insertAllUsers(entites)
        }
    }

    override suspend fun getFromDb(dispatcher: CoroutineDispatcher): List<User> {
        return withContext(dispatcher) {
            dao.getAllUsers().map { it.map() }
        }
    }

    override suspend fun clearDb(dispatcher: CoroutineDispatcher) {
        withContext(dispatcher) {
            dao.deleteAllUsers()
        }
    }

    override suspend fun getUserById(id: Int, dispatcher: CoroutineDispatcher): User? {
        return withContext(dispatcher) {
            dao.getUserById(id)
        }
    }
}