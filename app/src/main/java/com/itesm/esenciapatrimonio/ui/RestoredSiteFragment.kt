package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
// import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
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
        _binding!!.descripcion.text = "Descripción / Información / Historia completa del lugar en forma de resumen. No cabe todo el texto aquí, está bien jaja. El texto que sigue es sólo de pruebas, no pasa si se mueve o lo que sea, ntp. \n\nPrueba con saltos de línea para ver que se renderice el texto adecuadamente. \n\nNo debería haber problema porque existe una scrollview. \n\nWotefo jdsfhdsajd, háganle un chems y una fursona al Dany pa que se afurre sdfjhajd. \n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sollicitudin risus lectus, at malesuada velit ornare eu. Vivamus et cursus mi, quis dictum turpis. Cras ut arcu metus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Donec hendrerit scelerisque fermentum. Nullam et commodo diam, eget posuere augue. Duis malesuada nisi id est cursus, at vehicula neque dictum. Pellentesque mattis nisl velit, ut ultrices ipsum fermentum vel. In a purus ut diam dapibus posuere ut at magna. \n\nIn posuere et ipsum id elementum. In hendrerit risus ac enim aliquam, in scelerisque massa semper. Suspendisse sem ipsum, porta non sem id, varius facilisis sapien. Fusce vitae augue eu lorem congue ullamcorper ac et dolor. Vivamus varius rhoncus massa, a pellentesque turpis pretium in. Ut erat diam, laoreet vel pharetra vitae, imperdiet sit amet libero. Donec sed arcu et nibh consectetur aliquam."
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

        _binding!!.verHistoria.setOnClickListener{
            val params: ViewGroup.LayoutParams = _binding!!.descripcion.getLayoutParams() as ViewGroup.LayoutParams
            params.height = (ViewGroup.LayoutParams.WRAP_CONTENT)
            _binding!!.descripcion.setLayoutParams(params)
            _binding!!.verHistoria.isVisible = false
        }

        _binding!!.verGaleria.setOnClickListener{

            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()

            val fragment = GalleryFragment()

            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}