package com.example.marketplacenm.authorization;

import com.example.marketplacenm.App
import com.example.marketplacenm.Setting
import com.example.marketplacenm.authorization.data.db.ShopDb
import com.example.marketplacenm.authorization.data.db.User
import com.example.marketplacenm.util.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


interface UserRepository {
    fun getUserByFirstName(firstName: String): Flow<ResultResponse<out User?,out String>>

    suspend fun insertUser(user: User): Flow<ResultResponse<out Nothing,out String>>

    suspend fun deleteUser(user: User): Flow<ResultResponse<out Nothing,out String>>

    suspend fun updateAvatar(user: User): Flow<ResultResponse<out Nothing,out String>>

   // suspend fun getAll():List<User>

    fun getCurrentUser(): String

    suspend fun login(firstName: String)
    suspend fun logout()
}


class UserRepositoryImpl(private val db: ShopDb = App.db) : UserRepository {

    override fun getUserByFirstName(firstName: String) = flow<ResultResponse<User?, String>> {
        kotlin.runCatching {
            db.userDao.getUserByFirstName(firstName)
        }.onSuccess {
            emit(ResultResponse(true, it, null))
        }.onFailure {
            emit(ResultResponse(false, null, it.message ?: "Ошибка при поиски имени"))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun insertUser(user: User) = flow<ResultResponse<Nothing, String>> {
        kotlin.runCatching {
            db.userDao.insertUser(user)
        }.onSuccess {
            emit(ResultResponse(true, null, null))
            //TODO может отдельно
            login(user.firstName)
        }.onFailure {
           emit(ResultResponse(false, null, it.message ?: "Ошибка при записи  пользователя в бд"))
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun deleteUser(user: User) = flow<ResultResponse<Nothing, String>> {
        kotlin.runCatching {
             db.userDao.deleteUser(user)
        }.onSuccess {
             emit(ResultResponse(true, null, null))
        }.onFailure {
            emit(ResultResponse(false, null, it.message ?: "Ошибка при при удаление пользователя"))
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun updateAvatar(user: User)  = flow<ResultResponse<Nothing, String>> {
        kotlin.runCatching {
             db.userDao.update(user)
        }.onSuccess {
             emit(ResultResponse(true, null, null))
        }.onFailure {
             emit(ResultResponse(false, null, it.message ?: "Ошибка при обновление данных"))
        }
    }.flowOn(Dispatchers.IO)

   /* override suspend fun getAll(): List<User> {
       return  db.userDao.getAll()
    }*/

    override fun getCurrentUser(): String {
       return    Setting.userSigned?:""
    }

    override suspend fun login(firstName: String) {
        Setting.userSigned=firstName
    }

    override suspend fun logout() {
        Setting.userSigned=null
    }


}