package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
// import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.itesm.esenciapatrimonio.DetallesAdapter
import com.itesm.esenciapatrimonio.R
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
        _binding!!.placeID.setTextIsSelectable(true)

        _binding!!.Direccion.text = "Dirección: "+site.address
        _binding!!.Direccion.setTextIsSelectable(true)

        _binding!!.Coordenadas.text = "Coordenadas:\n\tX: "+site.coordinate_x.toString()+"\n\tY: "+site.coordinate_y.toString()
        _binding!!.Coordenadas.setTextIsSelectable(true)

        _binding!!.placeID.text = "Id del lugar: "+site.objectId
        _binding!!.placeID.setTextIsSelectable(false)
        _binding!!.placeID.isVisible = false

        _binding!!.dates.text = "Fechas\n\tEst year: "+site.est_year.toString()+"\n\tAño de restauración: "+site.restore_year
        _binding!!.dates.setTextIsSelectable(true)
        _binding!!.dates.isVisible = false

        _binding!!.verMasDetalles.setOnClickListener {
            _binding!!.verMasDetalles.isVisible = false
            _binding!!.placeID.isVisible = true
            _binding!!.dates.isVisible = true
        }

        // Sirve para ocultar el boton
        //_binding!!.verMasDetalles.isVisible = false

        // Cambiar el tamano del placdid
        //_binding!!.placeID.height = 500

        //val recyclerview = _binding!!.masDetalles.recycledViewPool
        //val adapter = DetallesAdapter()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}