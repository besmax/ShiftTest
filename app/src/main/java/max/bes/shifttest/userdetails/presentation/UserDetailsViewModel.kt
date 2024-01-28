package max.bes.shifttest.userdetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import max.bes.shifttest.core.domain.ExternalNavigator
import max.bes.shifttest.users.domain.models.ErrorType
import max.bes.shifttest.users.domain.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: UserRepository,
    private val externalNavigator: ExternalNavigator
) : ViewModel() {

    private val _screenState = MutableLiveData<UserDetailsScreenState>()
    val screenState: LiveData<UserDetailsScreenState> = _screenState
    private val userId = savedStateHandle.get<Int>("userId")

    init {
        if (userId != null) {
            getUser(userId)
        }
    }

    fun getUser(id: Int) {
        _screenState.value = UserDetailsScreenState.Loading
        viewModelScope.launch {
            val user = repository.getUserById(id, Dispatchers.IO)
            if (user != null) {
                _screenState.postValue(UserDetailsScreenState.Content(user))
            } else {
                _screenState.postValue(UserDetailsScreenState.Error(ErrorType.NO_CONTENT))
            }
        }
    }

    fun sendEmail(address: String) {
        externalNavigator.sendEmail(address)
    }

    fun makePhoneCall(phoneNumber: String) {
        externalNavigator.makePhoneCall(phoneNumber)
    }


    fun openMap(latitude: String, longitude: String) {
        externalNavigator.openMap(latitude, longitude)
    }

}