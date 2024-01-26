package max.bes.shifttest.core.data.network

import max.bes.shifttest.core.data.dto.responses.Response

interface NetworkClient {

    suspend fun doRequest(request: Any) : Response

}