package com.example.marketplacenm.authorization.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class ShopDb: RoomDatabase() {
    abstract val userDao: UserDao

    companion object{
        const val DATABASE_NAME = "shopDb"
    }
}