package com.example.marketplacenm.item.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplacenm.home.data.js.DetailsItem
import com.example.marketplacenm.home.data.repository.AppNetworkRepo
import com.example.marketplacenm.home.data.repository.NetworkRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ItemVM(private val repo: NetworkRepo = AppNetworkRepo()
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


}



data class ItemStateUI(val detailsItem: DetailsItem?=null, val selectColor:Int?=null,  val quantity:Double=0.0 ){

    fun getSum()= (detailsItem?.price?:0) * quantity

}




