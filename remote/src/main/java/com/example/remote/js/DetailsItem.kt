package com.example.remote.js

data class DetailsItem(
    val colors: List<String>,
    val description: String,
    val image_urls: List<String>,
    val name: String,
    val number_of_reviews: Int,
    val price: Double,
    val rating: Double
)

