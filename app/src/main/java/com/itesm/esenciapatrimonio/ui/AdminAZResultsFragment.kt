package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itesm.esenciapatrimonio.*
import com.itesm.esenciapatrimonio.databinding.FragmentAdminAzResultsBinding


class AdminAZResultsFragment: Fragment() {
    private var _binding: FragmentAdminAzResultsBinding? = null
    private val binding get() = _binding!!

    //restored site array from data base
    lateinit var sortedRestoredSite: List<SRestoreSite>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminAzResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Cargar los datos de parse y guaradrlos en el arereglo restoredSite
        //val parse = ParseApp()
        ParseApp.getAllRestoreSite(this::getRestoredSite)

        return root
    }

    /*
    Funcion para obetener los datos de parse
     */
    fun getRestoredSite(listRestoredSite:MutableList<SRestoreSite>):Unit{
        // Ordenar los datos de restoredSite por nombre
        sortedRestoredSite = listRestoredSite.sortedBy { it.site_name }

        //variable para controlar el elemento de recycler view
        val recycler = view?.findViewById<RecyclerView>(R.id.recycler_azResult)

        // Mostrar en la view cada elemento de la lista como boton
        var layoutManager: RecyclerView.LayoutManager? = null
        var adapter: RecyclerView.Adapter<AdminAZResultAdapter.ViewHolder>? = null

        layoutManager = LinearLayoutManager(activity)
        recycler?.layoutManager = layoutManager

        //Adaptador del Recycler View
        adapter = AdminAZResultAdapter(sortedRestoredSite, this)
        recycler?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}