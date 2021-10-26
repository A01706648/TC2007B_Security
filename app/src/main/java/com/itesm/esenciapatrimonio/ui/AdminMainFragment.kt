package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.R
import com.itesm.esenciapatrimonio.databinding.FragmentAdminMainBinding

/**
 * @author e-corp
 *
 * Fragmento donde el administrador al pulsar cualquiera de los dos botones
 * lo manda a la vista de agregar sitio o editar sitio.
 */

class AdminMainFragment: Fragment() {
    private var _binding: FragmentAdminMainBinding? = null

    // Esta propiedad sólo es válida entre onCreateView y onDestroyView.
    private val binding get() = _binding!!

    /**
     * Método onCreate que infla la vista en la interfaz establecida por el fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdminMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Botón que al seleccionarlo nos redirecciona a la vista de AdminAddSiteFragment
        // para agregar un nuevo sitio.
        binding.buttonAddSite.setOnClickListener{
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val fragment = AdminAddSiteFragment()
            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        // Botón que al seleccionarlo nos redirecciona a la vista de AdminAzResultsFragment
        // para editar el sitio.
        binding.buttonPhotos.setOnClickListener{
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val fragment = AdminAZResultsFragment()

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