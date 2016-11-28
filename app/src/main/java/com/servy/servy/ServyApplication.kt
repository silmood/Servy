package com.servy.servy

import android.app.Application
import android.content.Context
import io.fabric.sdk.android.Fabric

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
