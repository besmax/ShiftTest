package max.bes.shifttest.users.presentation

import max.bes.shifttest.users.domain.models.ErrorType
import max.bes.shifttest.users.domain.models.User

sealed interface UsersScreenState {
    data class Content(val users: List<User>) : UsersScreenState
    object Loading : UsersScreenState
    data class Error(val error: ErrorType) : UsersScreenState
}
