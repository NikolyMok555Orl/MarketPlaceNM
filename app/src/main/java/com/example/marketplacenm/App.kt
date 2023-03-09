package com.example.marketplacenm

import android.app.Application
import com.example.marketplacenm.authorization.data.db.ShopDb
import com.example.marketplacenm.home.data.api.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App:Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { ShopDb.getDatabase(this) }


    override fun onCreate() {
        super.onCreate()
        db = database
        Setting.init(this)
        api= Api.get()
    }

    companion object {
        lateinit var db: ShopDb
        lateinit var api: Api
    }
}