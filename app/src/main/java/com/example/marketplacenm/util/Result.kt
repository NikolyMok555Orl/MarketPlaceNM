package com.example.marketplacenm.util




data class ResultResponse <T, E>(
    val success: Boolean,
    val data: T?,
    val error: E?,
)