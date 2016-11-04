package com.servy.servy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.servy.servy.common.inflate
import kotlinx.android.synthetic.main.item_platillo.view.*

class MenuAdapter(val context: Context, val platillos: List<Platillo>) : RecyclerView.Adapter<MenuAdapter.PlatilloHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloHolder {
        val vistaItem = parent.inflate(R.layout.item_platillo)
        return PlatilloHolder(vistaItem)
    }

    override fun onBindViewHolder(holder: PlatilloHolder?, position: Int) {
        val platillo = platillos[position]
        holder?.popularItem(platillo)
    }

    override fun getItemCount(): Int {
        return platillos.count()
    }


    class PlatilloHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun popularItem(platillo: Platillo?) {
            itemView.labelNombre.text = platillo?.nombre
            itemView.labelPrecio.text = platillo?.precio.toString()
        }
    }
}
