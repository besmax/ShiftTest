package max.bes.shifttest.userdetails.presentation

import max.bes.shifttest.users.domain.models.ErrorType
import max.bes.shifttest.users.domain.models.User

sealed interface UserDetailsScreenState {
    data class Content(val user: User) : UserDetailsScreenState
    object Loading : UserDetailsScreenState
    data class Error(val error: ErrorType) : UserDetailsScreenState
}