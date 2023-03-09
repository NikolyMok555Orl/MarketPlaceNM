package com.example.marketplacenm.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplacenm.authorization.UserRepository
import com.example.marketplacenm.authorization.UserRepositoryImpl
import com.example.marketplacenm.authorization.data.db.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class ProfileVM(private val userRepo: UserRepository = UserRepositoryImpl()) : ViewModel() {


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


}


data class ProfileStateUI(val user: User? = null, val thisAvatar:String?=null)