package com.servy.servy.database

object PlatilloDbSchema {

    object PlatilloTable {

        const val NAME = "platillos"

        object Column {
            const val ID = "id"
            const val NOMBRE = "nombre"
            const val PRECIO = "precio"
            const val IMG_URL = "imagen"
        }
    }

}
