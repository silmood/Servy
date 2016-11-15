package com.servy.servy.common

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.servy.servy.BuildConfig
import java.util.*
import kotlin.reflect.KClass

fun Activity.startActivityWithSimpleIntent(kClass: KClass<out Activity>) {
    val activityIntent = Intent(applicationContext, kClass.java)
    startActivity(activityIntent)
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun TimePicker.setTime(date: Date) {
    val calendar = Calendar.getInstance()

    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val min = calendar.get(Calendar.MINUTE)

    if (Build.VERSION.SDK_INT < 23) {
        currentHour = hour
        currentMinute = min
    } else {
        setHour(hour)
        minute = min
    }
}

fun SQLiteDatabase.simpleQuery(tableName: String, whereClause: String?, whereArgs: Array<String>?): Cursor {
    return query(tableName,
            null,
            whereClause,
            whereArgs,
            null,
            null,
            null)
}