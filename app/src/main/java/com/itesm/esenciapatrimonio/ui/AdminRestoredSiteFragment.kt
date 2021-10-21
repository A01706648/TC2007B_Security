package com.itesm.esenciapatrimonio.ui

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.R
import com.itesm.esenciapatrimonio.databinding.FragmentAdminSitioRestauradoBinding
import com.itesm.esenciapatrimonio.transactions.TransactionData

class AdminRestoredSiteFragment: Fragment() {

    private var _binding: FragmentAdminSitioRestauradoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminSitioRestauradoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val site = TransactionData.restoredSite[0];

        //Log.d("Sitio restaurado", "${site.site_name}")

        _binding!!.Titulo.setText(site.site_name)

        _binding!!.descripcion.setText(site.information)

        _binding!!.Direccion.text = "Dirección: " + site.address
        _binding!!.Direccion.setTextIsSelectable(true)

        _binding!!.Coordenadas.text =
            "Coordenadas:\n\tX: " + site.coordinate_x.toString() + "\n\tY: " + site.coordinate_y.toString()
        _binding!!.Coordenadas.setTextIsSelectable(true)

        _binding!!.placeID.text = "Id del lugar: " + site.objectId
        _binding!!.placeID.setTextIsSelectable(false)
        _binding!!.placeID.isVisible = false

        _binding!!.dates.text =
            "Fechas\n\tEst year: " + site.est_year.toString() + "\n\tAño de restauración: " + site.restore_year
        _binding!!.dates.setTextIsSelectable(true)
        _binding!!.dates.isVisible = false

        _binding!!.verGaleria.setOnClickListener {

            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()

            val fragment = AdminGalleryFragment()

            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()

        }

        _binding!!.guardarNameAndDesc.setOnClickListener{
            //TODO: Here goes the code to save the Name and Description of the place if edited
        }

        _binding!!.eliminarSitio.setOnClickListener{
            val builder = AlertDialog.Builder(it.context)
            builder.setMessage("¿Estás seguro de eliminar el sitio?")
                .setCancelable(false)
                .setPositiveButton("SÍ") { dialog, id ->
                    //TODO: Here goes the code to delete the entire site








                    Toast.makeText(context, "Sitio eliminado", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("NO, NO ELIMINAR") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        //Verificar que el dispositivo este conectado a internet o utilizando datos
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) {
        }else {
            Toast.makeText(context,"Error de conexión", Toast.LENGTH_SHORT).show()
        }

        return root
    }

}