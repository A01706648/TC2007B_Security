package com.itesm.esenciapatrimonio.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
// import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Gallery
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itesm.esenciapatrimonio.R
import com.itesm.esenciapatrimonio.transactions.TransactionData
import com.itesm.esenciapatrimonio.databinding.FragmentSitioRestauradoBinding


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
            _binding!!.placeID.isVisible = false
            _binding!!.dates.isVisible = true
        }

        _binding!!.verHistoria.setOnClickListener{
            val params: ViewGroup.LayoutParams = _binding!!.descripcion.getLayoutParams() as ViewGroup.LayoutParams
            params.height = (ViewGroup.LayoutParams.WRAP_CONTENT)
            _binding!!.descripcion.setLayoutParams(params)
            _binding!!.verHistoria.isVisible = false
        }

        _binding!!.verGaleria.setOnClickListener{

            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()

            //val fragment = AdminAddPhotosFragment()
            val fragment = GalleryFragment()

            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()

        }

        //Verificar que el dispositivo este conectado a internet o utilizando datos
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) {
        }else {
            Toast.makeText(context,"Error de conexión, verifique que su dispositivo esté conectado a internet", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
