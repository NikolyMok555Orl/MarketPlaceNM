package com.example.remote.js

/**Последнее просмотренные*/
data class Latest(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
)

data class Latestes(val latest:List<Latest>)



