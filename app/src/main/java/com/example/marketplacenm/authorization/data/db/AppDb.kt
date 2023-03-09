package com.example.marketplacenm.authorization.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [User::class], version = 1)
abstract class ShopDb: RoomDatabase() {
    abstract val userDao: UserDao


    companion object{
        const val DATABASE_NAME = "shopDb"

        @Volatile
        private var INSTANCE: ShopDb? = null
        fun getDatabase(context: Context): ShopDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShopDb::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}