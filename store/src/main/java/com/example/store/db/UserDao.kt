package com.example.store.db

import androidx.room.*
import com.example.store.enity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE firstName = :firstName")
    fun getUserByFirstName(firstName: String): User?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)


    @Query("SELECT * FROM user")
    suspend fun getAll():List<User>

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun update(user: User)
}