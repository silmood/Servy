package com.servy.servy.common

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.reflect.KClass

fun Activity.startActivityWithSimpleIntent(kClass: KClass<out Activity>) {
    val activityIntent = Intent(applicationContext, kClass.java)
    startActivity(activityIntent)
}

fun ViewGroup.inflate(layoutId : Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun SQLiteDatabase.simpleQuery(tableName: String, whereClause : String?, whereArgs : Array<String>?) : Cursor {
    return query(tableName,
            null,
            whereClause,
            whereArgs,
            null,
            null,
            null)
}