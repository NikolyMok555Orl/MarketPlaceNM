package com.example.remote.api

import com.example.remote.js.DetailsItem
import com.example.remote.js.HintsWord
import com.example.remote.js.ItemsSale
import com.example.remote.js.Latestes
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface Api: ApiApp {

        @GET("/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
        override suspend fun getLatest(): Latestes

        @GET("/v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
        override suspend fun getSale(): ItemsSale


        @GET("/v3/f7f99d04-4971-45d5-92e0-70333383c239")
        override suspend fun getDetails(): DetailsItem


        @GET("/v3/4c9cd822-9479-4509-803d-63197e5a9e19")
        override suspend fun search(): HintsWord


        companion object {

                const val BASE_URL = "https://run.mocky.io"

                private var api: Api? = null

                private fun createApi() {
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
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .client(client)
                                .build()
                        api = retrofit.create(Api::class.java)
                }

                fun get(): Api {
                        if (api == null)
                                createApi()
                        return api!!
                }
        }

}


interface ApiApp {


        suspend fun getLatest(): Latestes


        suspend fun getSale(): ItemsSale


        suspend fun getDetails(): DetailsItem


        suspend fun search(): HintsWord

}