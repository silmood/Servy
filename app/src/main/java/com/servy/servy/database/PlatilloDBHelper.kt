package com.servy.servy.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import com.servy.servy.ServyApplication
import com.servy.servy.database.PlatilloDbSchema.PlatilloTable
import java.io.FileOutputStream
import java.io.IOException

const val VERSION = 1
const val DATABASE_NAME = "platillos.db"

class PlatilloDBHelper : SQLiteAssetHelper {
    constructor(context: Context) : super(context, DATABASE_NAME, null, VERSION) {
    }
}
