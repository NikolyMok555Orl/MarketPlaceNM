package com.example.authorization.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.store.enity.User
import com.example.marketplacenm.util.isEmailValid
import com.example.store.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class SignVM @Inject constructor(private val userRepo: UserRepository) : ViewModel() {


    private var _state: MutableStateFlow<SignStateUI> = MutableStateFlow(SignStateUI("", "", ""))
    var state: StateFlow<SignStateUI> = _state


    /**Для сообщений*/
    private val _sharedFlowError = MutableSharedFlow<String?>()
    val sharedFlowError = _sharedFlowError.asSharedFlow()


    fun setFirstName(firstName: String) {
        _state.value = _state.value.copy(firstName = firstName)
    }

    fun setLastName(lastName: String) {
        _state.value = _state.value.copy(lastName = lastName)
    }


    fun setEmail(email: String) {
        if (email.isEmailValid()) {
            _state.value = _state.value.copy(email = email, emailIsError = false)
        } else {
            _state.value = _state.value.copy(email = email, emailIsError = true)
        }
    }


    fun sign() {
        viewModelScope.launch {
            if (_state.value.firstName.isEmpty()) {
                _sharedFlowError.emit("First name is empty")
                return@launch
            }
            if (_state.value.lastName.isEmpty()) {
                _sharedFlowError.emit("LastName is empty")
                return@launch

            }
            if (_state.value.email.isEmpty()) {
                _sharedFlowError.emit("Email is empty")
                return@launch

            }
            if (!_state.value.email.isEmailValid()) {
                _sharedFlowError.emit("Email is not valid")
                return@launch
            }
            userRepo.getUserByFirstName(state.value.firstName).collect {
                if (it.success) {
                    if (it.data == null) {
                        with(state.value) {
                            userRepo.insertUser(
                                User(
                                    firstName = firstName,
                                    lastName = lastName,
                                    email = email,
                                    "",
                                    ""
                                )
                            ).collect { resIns ->
                                if (resIns.success) {
                                    _state.value = _state.value.copy(isLogin = true)
                                } else {
                                    _sharedFlowError.emit("Error sign")
                                }
                            }
                        }
                    } else {
                        _sharedFlowError.emit("A user with the same name already exists")
                        return@collect
                    }
                } else {
                    _sharedFlowError.emit("${it.error}")
                    return@collect
                }

            }


        }

    }

    companion object {

       /* fun getProvideFactory(
            userRepo: UserRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(SignVM::class.java)) {
                        return SignVM(
                            userRepo
                        ) as T
                    }
                    throw IllegalArgumentException("ViewModel не ProfileModel")
                }
            }*/

    }
}


data class SignStateUI(
    val firstName: String,
    val lastName: String,
    val email: String,
    val emailIsError: Boolean = false,
    val isLogin: Boolean = false
)


