package com.itesm.esenciapatrimonio

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.itesm.esenciapatrimonio.transactions.TransactionData
import com.itesm.esenciapatrimonio.ui.FullScreenImageFragment
import com.itesm.esenciapatrimonio.ui.GalleryFragment
import com.squareup.picasso.Picasso

class AdminGalleryAdapter: RecyclerView.Adapter<AdminGalleryAdapter.ViewHolder>(){

    private var listener: AdminGalleryAdapterClickEvents? = null

    fun setListener(listener: AdminGalleryAdapterClickEvents){
        this.listener = listener
    }

    interface AdminGalleryAdapterClickEvents {
        fun onClick(url: String)
    }

    companion object {
        private lateinit var categorias: Array<String>// = arrayOf("Card 1")

        private lateinit var imagenes_antiguas: Array<String> /*= arrayOf(
        "https://images.unsplash.com/photo-1480074568708-e7b720bb3f09?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2074&q=80"
    )
    */

        private lateinit var imagenes_actuales: Array<String>/* = arrayOf(
        "https://images.unsplash.com/photo-1564013799919-ab600027ffc6?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2070&q=80"
    )*/
        private lateinit var compareObjIdex_list:MutableList<String>

        fun initData()
        {
            val listCategorias:MutableList<String> = mutableListOf()
            val listImagenes_antiguas:MutableList<String> = mutableListOf()
            val listImagenes_actuales:MutableList<String> = mutableListOf()
            compareObjIdex_list = mutableListOf()

            for(comp in TransactionData.listCompare){
                listCategorias.add(comp.description)
                listImagenes_actuales.add(comp.newPic_id)
                listImagenes_antiguas.add(comp.oldPic_id)
                compareObjIdex_list.add(comp.objectId)
            }

            categorias = listCategorias.toTypedArray()
            imagenes_actuales = listImagenes_actuales.toTypedArray()
            imagenes_antiguas = listImagenes_antiguas.toTypedArray()
        }
    }



    private var listCompare :MutableList<SComparePicture> ?= null

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var imagen_antigua : ImageView
        var imagen_actual : ImageView
        var categoria : TextView
        var deleteButton: Button


        init{
            imagen_antigua = itemView.findViewById(R.id.imagenAntigua)
            imagen_actual = itemView.findViewById(R.id.imagenActual)
            categoria = itemView.findViewById(R.id.categoriaCarta)
            deleteButton = itemView.findViewById(R.id.deleteBlock)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_admin_gallery_block, parent, false)

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
        holder.imagen_antigua.setOnClickListener{
            listener?.onClick(imagenes_antiguas[position])
        }

        holder.imagen_actual.setOnClickListener{
            listener?.onClick(imagenes_actuales[position])
        }

        holder.deleteButton.setOnClickListener{
            val builder = android.app.AlertDialog.Builder(it.context)
            builder.setMessage("¿Estás seguro de eliminar las imágenes?")
                .setCancelable(false)
                .setPositiveButton("SÍ") { _, _ ->
                    //TODO: Here goes the code to delete the images with the string

                    val cm = it.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

                    if (activeNetwork != null && activeNetwork.isConnected){

                        ParseApp.deleteComparePicture(compareObjIdex_list[position])
                        Toast.makeText(it.context,"Eliminando...", Toast.LENGTH_SHORT).show()
                        Toast.makeText(it.context, "Imágenes eliminadas", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        Toast.makeText(it.context,"Error de conexión", Toast.LENGTH_SHORT).show()
                    }

                }
                .setNegativeButton("NO, NO ELIMINARLAS") { dialog, _ ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

}
