package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.databinding.FragmentDonacionGraciasBinding

class ThanksDonationFragment: Fragment() {

    private var _binding: FragmentDonacionGraciasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDonacionGraciasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding!!.continuarBoton.setOnClickListener{

        }

        return root
    }

    interface IOnBackPressed {
        fun onBackPressed(): Boolean
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}