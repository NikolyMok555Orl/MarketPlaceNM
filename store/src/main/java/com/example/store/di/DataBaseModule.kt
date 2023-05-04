package com.example.store

import android.app.Application
import android.content.Context
import com.example.store.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext appContext: Context): ShopDb = ShopDb.getDatabase(appContext)

    @Provides
    @Singleton
    fun provideSetting(@ApplicationContext context: Context) = Setting(context)

    @Provides
    fun provideChannelDao(appDatabase: ShopDb): UserDao {
        return appDatabase.userDao
    }

    @Singleton
    @Provides
    fun  getUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }



}