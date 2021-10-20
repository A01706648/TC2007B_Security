package com.itesm.esenciapatrimonio.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.databinding.FragmentDonacionBinding

class DonateFragment: Fragment() {
    private var _binding: FragmentDonacionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDonacionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Cantidad inicial
        var money = 5
        // Currency
        var curr = "USD"
        // La forma de Hu
        var texto = _binding!!.cantidad
        // URL
        var url = "https://paypal.me/FoxWare/"+money+curr

        texto.text = "$"+money+" "+curr

        _binding!!.botonMenos.setOnClickListener {
            if (money == 5){
                // Hacer nada
            }
            else if(money >=10){
                money -= 5
                texto.text = "$"+money+" "+curr
                url = "https://paypal.me/FoxWare/"+money+curr
            }
        }

        _binding!!.botonMas.setOnClickListener {
            money += 5
            texto.text = "$"+money+" "+curr
            url = "https://paypal.me/FoxWare/"+money+curr
        }

        _binding!!.botonDonar.setOnClickListener{
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse(url)
            startActivity(openURL)
            // TODO hacer que funcione goToThanks()
        }

        // TODO: Hacer que el usuario vaya a fragment_donacion_gracias
        /*
        fun goToThanks(site: SRestoreSite): Boolean {
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val fragment = RestoredSiteFragment()

            TransactionData.restoredSite = mutableListOf(site)

            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
            return true
        }
         */

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}