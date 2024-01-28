package max.bes.shifttest.core.data.network

import android.content.Context
import android.util.Log
import max.bes.shifttest.core.data.dto.requests.UsersRequest
import max.bes.shifttest.core.data.dto.responses.Response

class RetrofitNetworkClient(
    private val context: Context,
    private val randomUserApiService: RandomUserApiService
) : NetworkClient {
    override suspend fun doRequest(request: Any): Response {
        if (!ConnectionChecker.isConnected(context)) {
            return Response().apply { resultCode = CODE_NO_INTERNET }
        }

        return when (request) {
            is UsersRequest -> getUsers(request)
            else -> Response().apply { resultCode = CODE_WRONG_REQUEST }

        }
    }

    private suspend fun getUsers(request: UsersRequest): Response {
        return try {
            val response = randomUserApiService.getUsers()
            if (response.code() == CODE_SUCCESS && response.body() != null) {
                Log.d("RetrofitNetworkClient", response.body().toString())
                response.body()!!.apply { resultCode = CODE_SUCCESS }
            } else {
                Response().apply { resultCode = CODE_SERVER_ERROR }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Response().apply { resultCode = CODE_SERVER_ERROR }
        }
    }

    companion object {
        const val CODE_NO_INTERNET = -1
        const val CODE_SUCCESS = 200
        const val CODE_WRONG_REQUEST = 400
        const val CODE_SERVER_ERROR = 500
    }
}