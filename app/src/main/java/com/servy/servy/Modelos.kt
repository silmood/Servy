package com.servy.servy

data class Platillo(val id: String, val nombre: String, val precio: Float , val urlImagen: String?)
data class ItemOrden(var cantidad: Int, val platillo: Platillo)
