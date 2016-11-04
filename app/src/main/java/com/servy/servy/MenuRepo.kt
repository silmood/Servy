package com.servy.servy

import android.content.Context


public object MenuRepo {

    fun getMenu (context: Context) : List<Platillo>{

        val platillos = mutableListOf<Platillo>()

        for (index in 1..10){
            platillos.add(Platillo("Platillo $index", (index * 10.2).toFloat(), ""))
        }

        return platillos
    }
}