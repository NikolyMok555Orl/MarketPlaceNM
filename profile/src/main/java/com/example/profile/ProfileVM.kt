package com.example.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.store.UserRepository
import com.example.store.enity.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class ProfileVM(private val userRepo: UserRepository ) : ViewModel() {


    /**Для сообщений*/
    private val _sharedFlowError = MutableSharedFlow<String?>()
    val sharedFlowError = _sharedFlowError.asSharedFlow()

    private var _state: MutableStateFlow<ProfileStateUI> = MutableStateFlow(ProfileStateUI())
    var state: StateFlow<ProfileStateUI> = _state

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
                userRepo.getUserByFirstName(userRepo.getCurrentUser()).collect { user ->
                    if (user.success) {
                        _state.value = _state.value.copy(user = user.data, thisAvatar = user.data?.avatar)
                    }
                }
            }
    }

    fun changeAvatar(image: String) {
        viewModelScope.launch {
            _state.value.user?.let {
                userRepo.updateAvatar(it.copy(avatar = image)).collect{res->
                    if(!res.success){
                        _sharedFlowError.emit(res.error)
                    }
                }
            }
            _state.value = _state.value.copy(thisAvatar = image)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepo.logout()
        }
    }

    companion object{
        fun getProvideFactory(
            userRepo: UserRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ProfileVM::class.java)) {
                        return ProfileVM(
                            userRepo
                        ) as T
                    }
                    throw IllegalArgumentException("ViewModel не ProfileModel")
                }
            }


    }

}


data class ProfileStateUI(val user: User? = null, val thisAvatar:String?=null)