package max.bes.shifttest.core.data.dto.responses

import max.bes.shifttest.core.data.dto.InfoDto
import max.bes.shifttest.core.data.dto.UserDto

data class UsersResponse(
    val results: List<UserDto>,
    val info: InfoDto
) : Response()
