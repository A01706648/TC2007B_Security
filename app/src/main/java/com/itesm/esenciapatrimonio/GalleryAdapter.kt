package com.itesm.esenciapatrimonio

import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.itesm.esenciapatrimonio.transactions.TransactionData
import com.itesm.esenciapatrimonio.ui.FullScreenImageFragment
import com.itesm.esenciapatrimonio.ui.GalleryFragment
import com.squareup.picasso.Picasso

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.ViewHolder>(){

    private var listener: GalleryAdapterClickEvents? = null

    fun setListener(listener: GalleryAdapterClickEvents){
        this.listener = listener
    }

    interface GalleryAdapterClickEvents {
        fun onClick(url: String)
    }

    private val categorias = arrayOf("Card 1")

    private val imagenes_antiguas = arrayOf(
        "https://images.unsplash.com/photo-1480074568708-e7b720bb3f09?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2074&q=80"
    )

    private val imagenes_actuales = arrayOf(
        "https://images.unsplash.com/photo-1564013799919-ab600027ffc6?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2070&q=80"
    )

    private var listCompare :MutableList<SComparePicture> ?= null

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var imagen_antigua : ImageView
        var imagen_actual : ImageView
        var categoria : TextView


        init{
            imagen_antigua = itemView.findViewById(R.id.imagenAntigua)
            imagen_actual = itemView.findViewById(R.id.imagenActual)
            categoria = itemView.findViewById(R.id.categoriaCarta)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_gallery_block, parent, false)

        listCompare = null
        ParseApp.getAllComparePictureBySite(TransactionData.restoredSite[0].site_name){listComp ->
            listCompare = listComp
        }

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoria.text = categorias[position]

        Picasso.get().load(imagenes_antiguas[position]).into(holder.imagen_antigua)
        Picasso.get().load(imagenes_actuales[position]).into(holder.imagen_actual)

        /*
        Picasso.get().load(ParseApp.listImageTest[0]).into(holder.imagen_antigua)
        Picasso.get().load(ParseApp.listImageTest[1]).into(holder.imagen_actual)
         */

        /*Wait until the listComp is fetched by Parse*/
        /*
        while(listCompare == null){;}
        if(listCompare != null) {
            if (listCompare?.size != 0) {
                Picasso.get().load(listCompare!![position % listCompare!!.size].oldPic_id)
                    .into(holder.imagen_antigua)
                Picasso.get().load(listCompare!![position % listCompare!!.size].newPic_id)
                    .into(holder.imagen_actual)
            }
        }
*/
        holder.imagen_antigua.setOnClickListener{v: View ->
            listener?.onClick(imagenes_antiguas[position])
        }

        holder.imagen_actual.setOnClickListener{v: View ->
            listener?.onClick(imagenes_actuales[position])
        }
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

}
