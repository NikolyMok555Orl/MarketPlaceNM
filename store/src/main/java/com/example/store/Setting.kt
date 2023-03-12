package com.example.store

import android.content.Context
import android.content.SharedPreferences



class Setting( context: Context) {

    private val key = "com.example.app"
    private val prefs: SharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)

    private var _userSigned
        get() = prefs.getString(USER_SIGNED, null)
        set(value) {
            val editor = prefs.edit()
            editor.putString(USER_SIGNED, value)
            editor.apply()
        }

    companion object {
        private lateinit var settings: Setting

        fun init(c: Context) {
            settings = Setting(c)
            userSigned = settings._userSigned
        }
        var userSigned:String? = null
            set(value) {
                field = value
                settings._userSigned = value
            }



        const val USER_SIGNED = "user_signed"


    }

}


