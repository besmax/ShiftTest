package max.bes.shifttest.core.data.network

import max.bes.shifttest.core.data.dto.responses.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface RandomUserApiService {

    @GET("?results=500")
    suspend fun getUsers(): Response<UsersResponse>


}