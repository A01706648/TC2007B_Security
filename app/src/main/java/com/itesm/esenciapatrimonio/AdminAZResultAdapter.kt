package com.itesm.esenciapatrimonio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itesm.esenciapatrimonio.transactions.GoToRestoredSite
import com.itesm.esenciapatrimonio.transactions.TransactionData
import com.itesm.esenciapatrimonio.ui.AdminAZResultsFragment
import com.itesm.esenciapatrimonio.ui.AdminRestoredSiteFragment
import com.itesm.esenciapatrimonio.ui.RestoredSiteFragment

class AdminAZResultAdapter(sortedRestoredSite: List<SRestoreSite>,  fragment: AdminAZResultsFragment) : RecyclerView.Adapter<AdminAZResultAdapter.ViewHolder>() {

    private val azFragment = fragment
    private val listRestoredSite = sortedRestoredSite

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titulo : TextView
        var descripcion : TextView
        var boton : Button

        init {
            titulo = itemView.findViewById(R.id.tituloLugar)
            descripcion = itemView.findViewById(R.id.descripcionLugar)
            boton = itemView.findViewById(R.id.button_consult)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_az_results, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.titulo.text = listRestoredSite[i].site_name
        holder.descripcion.text = listRestoredSite[i].information
        holder.boton.setOnClickListener{v: View ->
            //llevar a la view del sitio restaurado correspondiente
            //Sobreescribimos el objeto TransactionData con el sitio restaurado seleccionado
            TransactionData.restoredSite = mutableListOf(listRestoredSite[i])
            //Hacemos la transaccion de fragmentos con la clase GoToRestoredSite
            GoToRestoredSite(azFragment, AdminRestoredSiteFragment()).makeTransaction()
        }
    }

    override fun getItemCount(): Int {
        return listRestoredSite.size
    }
}