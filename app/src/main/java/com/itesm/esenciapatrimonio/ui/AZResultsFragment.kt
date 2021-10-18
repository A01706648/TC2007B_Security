package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.ParseApp
import com.itesm.esenciapatrimonio.SRestoreSite
import com.itesm.esenciapatrimonio.databinding.FragmentAzResultsBinding

class AZResultsFragment: Fragment() {
    private var _binding: FragmentAzResultsBinding? = null
    private val binding get() = _binding!!

    //restored site array from data base
    lateinit var restoredSite: MutableList<SRestoreSite>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAzResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Cargar los datos de parse y guaradrlos en el arereglo restoredSite
        val parse = ParseApp()
        parse.getAllRestoreSite(this::getRestoredSite)

        // Ordenar los datos de restoredSite por nombre
        val sortedRestoredSite = restoredSite.sortedBy { it.site_name }

        // Mostrar en la view cada elemento de la lista como boton
        for (i in sortedRestoredSite) {
            Log.d("lista ordenada", "${i.site_name}")
        }

        // Al hacer click en el boton llevar al fragmento del sitio restaurado corrrespondiente

        return root
    }

    /*
    Funcion para obetener los datos de parse
     */
    fun getRestoredSite(listRestoredSite:MutableList<SRestoreSite>):Unit{
        restoredSite = listRestoredSite
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}