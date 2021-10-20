package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.ParseApp
import com.itesm.esenciapatrimonio.SRestoreSite
import com.itesm.esenciapatrimonio.databinding.FragmentAdminAddSiteBinding
import com.parse.Parse

class AdminAddSiteFragment: Fragment() {
    private var _binding: FragmentAdminAddSiteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdminAddSiteBinding.inflate(inflater, container, false)
        val root: View = binding.root



        _binding!!.botonAgregar.setOnClickListener{
            if(_binding!!.nombreSitio.text.toString() == ""){
                Toast.makeText(context, "Te falta poner el nombre del sitio", Toast.LENGTH_SHORT).show()
            }
            else if(_binding!!.description.text.toString() == ""){
                Toast.makeText(context, "Te falta poner la descripción del sitio", Toast.LENGTH_SHORT).show()
            }
            else if(_binding!!.direccionSitio.text.toString() == ""){
                Toast.makeText(context, "Te falta poner la direccion del sitio", Toast.LENGTH_SHORT).show()
            }
            else if(_binding!!.coordenadasXY.text.toString() == ""){
                Toast.makeText(context, "Te faltan las coordenadas del sitio", Toast.LENGTH_SHORT).show()
            }
            else if(_binding!!.anioConstruccion.text.toString() == ""){
                Toast.makeText(context, "Te falta poner el año de construcción", Toast.LENGTH_SHORT).show()
            }
            else if(_binding!!.anioRestauracion.text.toString() == ""){
                Toast.makeText(context, "Te falta poner el año de restauración", Toast.LENGTH_SHORT).show()
            }
            else{
                val str = _binding!!.coordenadasXY.text.toString()
                val list: List<String> = str.split(",").toList()
                if(list.size < 2 ){
                    Toast.makeText(context, "Falta una coordenada o hay algo mal", Toast.LENGTH_SHORT).show()
                }
                else{
                    val oNewSite = SRestoreSite(site_name = _binding!!.nombreSitio.text.toString(),
                        information = _binding!!.description.text.toString(),
                        est_year = _binding!!.anioConstruccion.text.toString().toInt(),
                        restore_year = _binding!!.anioRestauracion.text.toString().toInt(),
                        address = _binding!!.direccionSitio.text.toString(),
                        coordinate_x = list[0].toDouble(),
                        coordinate_y = list[1].toDouble())

                    //val oParse = ParseApp()
                    ParseApp.addRestoreSite(oNewSite, {listSite-> })
                        // For debug:
                        //Toast.makeText(context, "Salida de coordenadas "+list[0]+" "+list[1], Toast.LENGTH_SHORT).show()
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}