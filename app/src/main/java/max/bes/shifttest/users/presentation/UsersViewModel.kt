package max.bes.shifttest.users.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import max.bes.shifttest.core.domain.ExternalNavigator
import max.bes.shifttest.users.domain.models.ErrorType
import max.bes.shifttest.users.domain.repositories.UserRepository
import max.bes.shifttest.users.domain.usecases.GetUsersUseCase
import max.bes.shifttest.util.Resource
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val repository: UserRepository,
    private val externalNavigator: ExternalNavigator
) : ViewModel() {

    private val _screenState = MutableLiveData<UsersScreenState>()
    val screenState: LiveData<UsersScreenState> = _screenState
    private var job: Job? = null

    init {
        getUsers()
    }

    fun getUsers() {
        if (job?.isActive == true) return

        _screenState.value = UsersScreenState.Loading

        job = viewModelScope.launch {
            getUsersUseCase.execute(Dispatchers.IO).collect() { response ->
                when (response) {
                    is Resource.Success -> {
                        if (!response.data.isNullOrEmpty()) {
                            _screenState.postValue(UsersScreenState.Content(response.data))
                        } else {
                            _screenState.postValue(UsersScreenState.Error(ErrorType.NO_CONTENT))

                        }
                    }

                    is Resource.Error -> _screenState.postValue(
                        UsersScreenState.Error(
                            response.errorType ?: ErrorType.SERVER_ERROR
                        )
                    )
                }
            }
        }

    }

    fun updateUsersFromNetwork() {
        viewModelScope.launch {
            repository.clearDb(Dispatchers.IO)
            getUsers()
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