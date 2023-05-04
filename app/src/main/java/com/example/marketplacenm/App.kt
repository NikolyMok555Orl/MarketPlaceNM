package com.example.marketplacenm

import android.app.Application
import com.example.store.Setting
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application(){


    override fun onCreate() {
        super.onCreate()
        Setting.init(this)
    }


}


