package com.example.store

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//TODO под удалением
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideNoteDatabase(app: Application): ShopDb = ShopDb.getDatabase(app)

    @Provides
    @Singleton
    fun provideSetting(@ApplicationContext context: Context) = Setting(context)

    @Provides
    @Singleton
    fun provideNoteRepository(db: ShopDb): UserRepository = UserRepositoryImpl(db)



}