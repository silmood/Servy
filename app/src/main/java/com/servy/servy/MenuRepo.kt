package com.servy.servy

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.servy.servy.common.simpleQuery
import com.servy.servy.database.PlatilloCursorWrapper
import com.servy.servy.database.PlatilloDBHelper
import com.servy.servy.database.PlatilloDbSchema


object MenuRepo {

    val database : SQLiteDatabase by lazy {
        PlatilloDBHelper(ServyApplication.getAppContext())
                .writableDatabase
    }


    fun getMenu () : List<Platillo>{

        val platillos = mutableListOf<Platillo>()
        val cursor = PlatilloCursorWrapper(database.simpleQuery(PlatilloDbSchema.PlatilloTable.NAME, null, null))

        cursor.moveToFirst()

        while (!cursor.isAfterLast) {
            platillos.add(cursor.getPlatillo())
            cursor.moveToNext()
        }
        cursor.close()

        return platillos
    }
}