package com.example.marketplacenm.authorization.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val avatar: String
)