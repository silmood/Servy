package com.servy.servy.common

import android.app.Activity
import android.content.Intent
import kotlin.reflect.KClass

fun Activity.startActivityWithSimpleIntent(kClass: KClass<out Activity>) {
    val activityIntent = Intent(applicationContext, kClass.java)
    startActivity(activityIntent)
}