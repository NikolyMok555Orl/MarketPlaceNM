package com.example.item.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.remote.js.DetailsItem
import com.example.remote.repository.AppNetworkRepo
import com.example.remote.repository.NetworkRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject


@HiltViewModel
class ItemVM @Inject constructor(private val repo: NetworkRepo
):ViewModel() {


    private var _state: MutableStateFlow<ItemStateUI> = MutableStateFlow(ItemStateUI())
    var state: StateFlow<ItemStateUI> = _state


    fun selectColor(index:Int){
        _state.value=_state.value.copy(selectColor = index)
    }


    fun changeQuantity(q:Double){
       if(_state.value.quantity+q>=0){
           _state.value=_state.value.copy(quantity = (_state.value.quantity+q))
       }else{
           _state.value=_state.value.copy(quantity = 0.0)
       }
    }
    init {
        loadDetailsItem()
    }

    fun loadDetailsItem(){
        viewModelScope.launch {
            repo.getDetails().collect{
                if(it.success){
                    _state.value=_state.value.copy(detailsItem = it.data, selectColor = 0)
                }
            }


        }
    }

    companion object{
        fun getProvideFactory(
            networkRepo: NetworkRepo
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ItemVM::class.java)) {
                        return ItemVM(
                            networkRepo
                        ) as T
                    }
                    throw IllegalArgumentException("ViewModel не ProfileModel")
                }
            }



    }

}



data class ItemStateUI(val detailsItem: DetailsItem?=null, val selectColor:Int?=null, val quantity:Double=0.0 ){

    fun getSum()= (detailsItem?.price?:0.0) * quantity

}




