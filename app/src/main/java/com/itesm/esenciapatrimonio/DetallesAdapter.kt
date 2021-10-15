package com.itesm.esenciapatrimonio

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class DetallesAdapter: RecyclerView.Adapter<DetallesAdapter.ViewHolder>(){

    val detalles = arrayOf("Prueba 1", "Prueba 2")

    // Metodo cuando entra por primera vez a crear la recyclerview
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycler_detail_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.item_text.text = detalles[i]
    }

    override fun getItemCount(): Int {
        return detalles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var item_text: TextView

        init{
            item_text = itemView.findViewById(R.id.Direccion)
        }

    }
}