package com.servy.servy

import android.app.Application
import android.content.Context

class ServyApplication() : Application() {

    companion object {
        private lateinit var context: Context

        fun getAppContext() : Context{
            return  context
        }
    }

    override fun onCreate() {
        super.onCreate()
        ServyApplication.context = applicationContext
    }
}
