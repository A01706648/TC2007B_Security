package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.databinding.FragmentAdminMainBinding

class AdminMainFragment: Fragment() {
    private var _binding: FragmentAdminMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdminMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

        var  = 0

        binding.

        _binding!!.buttonAddSite.setOnClickListener{
            code_id = 1
            pickImageGallery(code_id)
            selectImage()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}