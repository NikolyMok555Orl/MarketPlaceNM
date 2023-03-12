package com.example.marketplacenm.util


@kotlinx.serialization.Serializable
data class NetworkResult <T, E>(
    val success: Boolean,
    val data: T?,
    val error: E?,
)