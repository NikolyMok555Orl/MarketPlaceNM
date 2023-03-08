package com.example.marketplacenm.authorization.data.db

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE firstName = :firstName")
    fun getUserByFirstName(firstName: String): User?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user:User)

    @Update
    suspend fun update(user:User)
}