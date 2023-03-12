package com.example.remote.js

import com.google.gson.annotations.SerializedName

data class ItemSale(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)


data class ItemsSale(@SerializedName("flash_sale") val sales: List<ItemSale>)