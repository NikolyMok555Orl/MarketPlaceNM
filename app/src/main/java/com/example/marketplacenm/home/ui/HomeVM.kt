package com.example.marketplacenm.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.marketplacenm.App
import com.example.marketplacenm.authorization.UserRepository
import com.example.marketplacenm.authorization.UserRepositoryImpl
import com.example.marketplacenm.authorization.data.db.User
import com.example.marketplacenm.authorization.ui.LoginStateUI
import com.example.marketplacenm.home.data.js.ItemSale
import com.example.marketplacenm.home.data.js.Latest
import com.example.marketplacenm.home.data.repository.AppNetworkRepo
import com.example.marketplacenm.home.data.repository.NetworkRepo
import com.example.marketplacenm.home.ui.component.Brand
import com.example.marketplacenm.home.ui.component.Group
import com.example.marketplacenm.home.ui.component.getBrands
import com.example.marketplacenm.home.ui.component.getGroup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeVM(private val networkRepo: NetworkRepo= AppNetworkRepo(), private val userRepository: UserRepository=UserRepositoryImpl(
    App.db)
):ViewModel() {


    private var _state: MutableStateFlow<HomeStateUI> = MutableStateFlow(HomeStateUI(category = getGroup(), brands = getBrands()))
    var state: StateFlow<HomeStateUI> = _state


    init {
        loadSale()
        loadLates()
        getUser()
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




}


data class HomeStateUI(val avatar:String?=null, val query: String="", val responseSearch:List<String> = emptyList(),
                    val latests: List<Latest> = emptyList(),val sales: List<ItemSale> = emptyList(), val category: List<Group> = emptyList(),
                    val   brands:List<Brand> = emptyList(), val user: User?=null
)