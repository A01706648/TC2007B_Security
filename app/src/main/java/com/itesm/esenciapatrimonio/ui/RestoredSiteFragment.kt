package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
// import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.TransactionData
import com.itesm.esenciapatrimonio.databinding.FragmentAzResultsBinding
import com.itesm.esenciapatrimonio.databinding.FragmentSitioRestauradoBinding
import org.w3c.dom.Text


class RestoredSiteFragment: Fragment() {
    private var _binding: FragmentSitioRestauradoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSitioRestauradoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val site = TransactionData.restoredSite[0];

        //Log.d("Sitio restaurado", "${site.site_name}")

        _binding!!.Titulo.text = site.site_name

        _binding!!.descripcion.text = site.information

        _binding!!.Direccion.text = "Dirección: "+site.address

        _binding!!.Coordenadas.text = "Coordenadas:\n\tX: "+site.coordinate_x.toString()+"\n\tY: "+site.coordinate_y.toString()

        _binding!!.placeID.text = "Id del lugar: "+site.objectId

        //val titulo = findViewById(R.id.Titulo) as TextView
        //titulo.text = site.site_name

        //val descripcion = findViewById(R.id.descripcion) as TextView
        //descripcion.text = site.information

        //val direccion = findViewById(R.id.Direccion) as TextView
        //direccion.text = site.address

        //val coords = findViewById(R.id.Coordenadas) as TextView
        //coords.text = site.coordinate_x.toString()+site.coordinate_y.toString()

        //val id = findViewById(R.id.placeID) as TextView
        //id.text = site.objectId

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}