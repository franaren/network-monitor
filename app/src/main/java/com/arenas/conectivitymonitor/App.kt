package com.arenas.conectivitymonitor

import android.app.Application
import android.content.Context

class App: Application() {
    init {
        instance = this
    }

    companion object {
        var instance: App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context = App.applicationContext()
    }
}