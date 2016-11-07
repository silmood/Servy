package com.servy.servy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.servy.servy.common.inflate
import kotlinx.android.synthetic.main.item_platillo.view.*

class MenuAdapter(val context: Context, val platillos: List<Platillo>) : RecyclerView.Adapter<MenuAdapter.PlatilloHolder>() {

    var orden: MutableList<Platillo> = mutableListOf<Platillo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloHolder {
        val vistaItem = parent.inflate(R.layout.item_platillo)
        return PlatilloHolder(vistaItem)
    }

    override fun onBindViewHolder(holder: PlatilloHolder?, position: Int) {
        val platillo = platillos[position]

        holder?.setSelectionListener { platilloSeleccionado ->
            val  estaEnOrden : Boolean = estaEnOrden(platilloSeleccionado)

            if (estaEnOrden)
                orden = orden.filter { platillo -> platillo.id != platilloSeleccionado.id }.toMutableList()
            else
                orden.add(platilloSeleccionado)
        }

        holder?.popularItem(platillo, estaEnOrden(platillo) )
    }

    fun estaEnOrden(platilloAComprobar: Platillo) : Boolean{
        var  estaEnOrden : Boolean = false

        orden.forEach {
            platillo -> estaEnOrden = estaEnOrden || platillo.id == platilloAComprobar.id

        }

        return estaEnOrden
    }

    override fun getItemCount(): Int {
        return platillos.count()
    }


    class PlatilloHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var onPlatilloSelected: ((Platillo) -> Unit)? = null

        fun popularItem(platillo: Platillo, estaEnOrden : Boolean) {
            itemView.labelNombre.text = platillo.nombre
            itemView.labelPrecio.text = platillo.precio.toString()
            itemView.checkbox.isChecked = estaEnOrden
            itemView.checkbox.setOnCheckedChangeListener { checkBox, checked ->
                onPlatilloSelected?.invoke(platillo)
            }

            Glide.with(ServyApplication.getAppContext())
                    .load(platillo.urlImagen)
                    .placeholder(R.drawable.burger)
                    .into(itemView.imgPlatillo)
        }

        fun setSelectionListener(onPlatilloSelected: (Platillo) -> Unit) {
            this.onPlatilloSelected = onPlatilloSelected
        }

    }
}
