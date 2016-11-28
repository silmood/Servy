package com.servy.servy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.servy.servy.common.inflate
import kotlinx.android.synthetic.main.item_platillo.view.*

class MenuAdapter(val context: Context, val platillos: List<Platillo>) : RecyclerView.Adapter<MenuAdapter.PlatilloHolder>() {

    var orden: MutableList<ItemOrden> = mutableListOf<ItemOrden>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloHolder {
        val vistaItem = parent.inflate(R.layout.item_platillo)
        return PlatilloHolder(vistaItem)
    }

    override fun onBindViewHolder(holder: PlatilloHolder?, position: Int) {
        val platillo = platillos[position]
        val cantidad = if (estaEnOrden(platillo)) obtenerCantidadDe(platillo) else 0

        holder?.setSelectionListener { platilloSeleccionado, cantidad ->
            val  estaEnOrden : Boolean = estaEnOrden(platilloSeleccionado)

            if (estaEnOrden && cantidad == 0)
                orden = orden.filter { item -> item.platillo.id != platilloSeleccionado.id }.toMutableList()
            else if (estaEnOrden)
                orden.forEach { item -> if (item.platillo.id == platilloSeleccionado.id) item.cantidad = cantidad }
            else
                orden.add(ItemOrden(cantidad, platilloSeleccionado))
        }

        holder?.popularItem(platillo, cantidad)
    }

    fun estaEnOrden(platilloAComprobar: Platillo) : Boolean{
        var  estaEnOrden : Boolean = false

        orden.forEach {
            item -> estaEnOrden = estaEnOrden || item.platillo.id == platilloAComprobar.id

        }

        return estaEnOrden
    }

    fun obtenerCantidadDe(platillo: Platillo) : Int {
        orden.forEach { item ->
            if (platillo.id == item.platillo.id)
                return  item.cantidad
        }

        return 0
    }

    override fun getItemCount(): Int {
        return platillos.count()
    }


    class PlatilloHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var onPlatilloSelected: ((Platillo, Int) -> Unit)? = null

        fun popularItem(platillo: Platillo, cantidad: Int) {
            itemView.labelNombre.text = platillo.nombre
            itemView.labelPrecio.text = platillo.precio.toString()
            itemView.checkbox.number = cantidad.toString()
            itemView.checkbox.setOnValueChangeListener { elegantNumberButton, oldValue, newValue ->
                onPlatilloSelected?.invoke(platillo, newValue)
            }


            Glide.with(ServyApplication.getAppContext())
                    .load(platillo.urlImagen)
                    .placeholder(R.drawable.burger)
                    .into(itemView.imgPlatillo)
        }

        fun setSelectionListener(onPlatilloSelected: (Platillo, Int) -> Unit) {
            this.onPlatilloSelected = onPlatilloSelected
        }

    }
}
