package com.example.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.remote.js.ItemSale
import com.example.remote.js.Latest
import com.example.remote.repository.AppNetworkRepo
import com.example.remote.repository.NetworkRepo
import com.example.home.ui.component.Brand
import com.example.home.ui.component.Group
import com.example.home.ui.component.getBrands
import com.example.home.ui.component.getGroup
import com.example.store.UserRepository
import com.example.store.enity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class HomeVM(private val networkRepo: NetworkRepo ,
             private val userRepository: UserRepository
):ViewModel() {


    private var _state: MutableStateFlow<HomeStateUI> = MutableStateFlow(HomeStateUI(category = getGroup(), brands = getBrands()))
    var state: StateFlow<HomeStateUI> = _state


    init {
        loadSale()
        loadLates()
        getUser()
    }


    fun search(q:String){
        _state.value=_state.value.copy(query = q)
        viewModelScope.launch {
            networkRepo.search(q).collect{res->
             if(res.success){
                 val listRes=res.data?.words?.filter { q in  it }?: emptyList()
                 _state.value=_state.value.copy(responseSearch = listRes)
             }
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            userRepository.getUserByFirstName(userRepository.getCurrentUser()).collect { user ->
                if (user.success) {
                    _state.value = _state.value.copy(user = user.data)
                }
            }
        }
    }
    private fun loadSale(){
        viewModelScope.launch {
            networkRepo.getSale().collect{
                if(it.success) {
                    _state.value = _state.value.copy(sales = it.data?.sales?: listOf())
                }
            }
        }
    }

    private fun loadLates(){
        viewModelScope.launch {
            networkRepo.getLatest().collect{
                if(it.success) {
                    _state.value = _state.value.copy(latests = it.data?.latest?: listOf())
                }
            }
        }
    }


    companion object{
        fun getProvideFactory(
            networkRepo: NetworkRepo ,
            userRepository: UserRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(HomeVM::class.java)) {
                        return HomeVM(
                            networkRepo,
                            userRepository
                        ) as T
                    }
                    throw IllegalArgumentException("ViewModel не ProfileModel")
                }
            }


    }

}


data class HomeStateUI(val avatar:String?=null, val query: String="", val responseSearch:List<String> = emptyList(),
                       val latests: List<Latest> = emptyList(), val sales: List<ItemSale> = emptyList(),
                       val category: List<Group> = emptyList(),
                       val   brands:List<Brand> = emptyList(), val user: User?=null
)