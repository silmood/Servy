package com.servy.servy.database

import android.database.Cursor
import android.database.CursorWrapper
import com.servy.servy.Platillo
import com.servy.servy.database.PlatilloDbSchema.PlatilloTable

class PlatilloCursorWrapper(cursor: Cursor) : CursorWrapper(cursor) {

    fun getPlatillo(): Platillo {
        val id = getString(getColumnIndex(PlatilloTable.Column.ID))
        val nombre = getString(getColumnIndex(PlatilloTable.Column.NOMBRE))
        val precio = getFloat(getColumnIndex(PlatilloTable.Column.PRECIO))
        val imagen = getString(getColumnIndex(PlatilloTable.Column.IMG_URL))

        return Platillo(id, nombre, precio, imagen)
    }
}
