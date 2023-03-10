package com.example.marketplacenm.authorization.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplacenm.authorization.UserRepository
import com.example.marketplacenm.authorization.UserRepositoryImpl
import com.example.marketplacenm.authorization.data.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginVM(private val userRepo: UserRepository = UserRepositoryImpl()):ViewModel() {



    private var _state: MutableStateFlow<LoginStateUI> = MutableStateFlow(LoginStateUI("", ""))
    var state: StateFlow<LoginStateUI> = _state


    /**Для сообщений*/
    private val _sharedFlowError = MutableSharedFlow<String?>()
    val sharedFlowError = _sharedFlowError.asSharedFlow()


    fun setFirstName(firstName: String) {
        _state.value = _state.value.copy(firstName = firstName)
    }

    fun setPassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun showPass(isShow:Boolean){
        _state.value = _state.value.copy(showPass = isShow)
    }

    fun login(){
        viewModelScope.launch {
            if (_state.value.firstName.isEmpty()) {
                _sharedFlowError.emit("First name is empty")
                return@launch
            }
            /*var listUser= emptyList<User>()
            withContext(Dispatchers.IO){
                listUser= userRepo.getAll()
            }*/
            userRepo.getUserByFirstName(state.value.firstName).collect {
                if(it.success){
                    if(it.data!=null){
                        userRepo.login(_state.value.firstName)
                        _state.value=_state.value.copy(isLogin=true)
                    }else{
                        _sharedFlowError.emit("User with this name does not exist")
                        return@collect
                    }
                }else{
                    _sharedFlowError.emit("${it.error}")
                    return@collect
                }

            }

        }
    }


}


data class LoginStateUI(val firstName:String, val password:String, val showPass:Boolean=false, val isLogin:Boolean=false)