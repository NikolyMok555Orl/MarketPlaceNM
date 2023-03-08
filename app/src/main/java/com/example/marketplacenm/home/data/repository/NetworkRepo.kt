package com.example.marketplacenm.home.data.repository

import android.telecom.Call
import com.example.marketplacenm.App
import com.example.marketplacenm.home.data.api.ApiApp
import com.example.marketplacenm.home.data.js.DetailsItem
import com.example.marketplacenm.home.data.js.ItemsSale
import com.example.marketplacenm.home.data.js.Latest
import com.example.marketplacenm.util.NetworkResult
import com.example.marketplacenm.util.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


interface NetworkRepo  {

        fun getLatest() : Flow<NetworkResult<out List<Latest>, out String>>
        fun getSale() : Flow<NetworkResult< out ItemsSale,out String>>
        fun getDetails() : Flow<NetworkResult<out DetailsItem,out String>>
        fun search(string: String) : Flow<NetworkResult<out List<String>,out String>>
}



class AppNetworkRepo(private val api: ApiApp = App.api):NetworkRepo{

    override fun getLatest() = flow {
        kotlin.runCatching {
            api.getLatest()
        }.onSuccess {
            emit(NetworkResult(true, it, null))
        }.onFailure {
            emit(NetworkResult(false, null, it.message ?: "При загрузки последних товаров"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getSale() = flow {
        kotlin.runCatching {
            api.getSale()
        }.onSuccess {
            emit(NetworkResult(true, it, null))
        }.onFailure {
            emit(NetworkResult(false, null, it.message ?: "При загрузки распродаж"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getDetails() = flow {
        kotlin.runCatching {
            api.getDetails()
        }.onSuccess {
            emit(NetworkResult(true, it, null))
        }.onFailure {
            emit(NetworkResult(false, null, it.message ?: "При получение инфы о товаре"))
        }
    }.flowOn(Dispatchers.IO)

    override fun search(string: String) = flow {
        kotlin.runCatching {
            api.search()
        }.onSuccess {
            emit(NetworkResult(true, it, null))
        }.onFailure {
            emit(NetworkResult(false, null, it.message ?: "При получение подсказок при поиск"))
        }
    }.flowOn(Dispatchers.IO)

}