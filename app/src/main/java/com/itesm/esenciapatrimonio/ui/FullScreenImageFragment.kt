package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.databinding.FragmentFullscreenImageBinding
import com.squareup.picasso.Picasso

/**
 * @author e-corp
 *
 * Fragmento donde el administrador podra hacer login en la aplicación
 * para contar con los beneficios de admin.
 */

class FullScreenImageFragment(private val imageURL: String): Fragment() {

    private var _binding: FragmentFullscreenImageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * Método onCreate que infla la vista en la interfaz establecida por el fragmento
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFullscreenImageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Picasso.get().load(imageURL).into(binding.imagenCompleta)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
