package com.example.remote.di

import android.content.Context
import android.content.pm.ApplicationInfo
import com.example.remote.api.Api
import com.example.remote.api.ApiApp
import com.example.remote.repository.AppNetworkRepo
import com.example.remote.repository.NetworkRepo
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun getAppApi():ApiApp{
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
            // this.mockinize(mocks)
            this.readTimeout(60, TimeUnit.SECONDS)
            this.connectTimeout(60, TimeUnit.SECONDS)
//                this.retryOnConnectionFailure(true)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        val api = retrofit.create(Api::class.java)
        return api
    }

    @Singleton
    @Provides
    fun  getAppNetworkRepo(apiApp: ApiApp): NetworkRepo {
        return AppNetworkRepo(apiApp)
    }

}