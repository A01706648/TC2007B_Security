package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.databinding.FragmentRegisterUserBinding
import com.mapbox.android.telemetry.errors.TokenChangeBroadcastReceiver.register

class AdminRegistrarUsuarioFragment: Fragment() {

    private var _binding: FragmentRegisterUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.botonLogin.setOnClickListener{
            val username:String = binding.Usuario.text.toString()
            val password:String = binding.contrasena.text.toString()
            val confirm:String = binding.contrasenaConfirm.text.toString()

            registerUser(username,password, confirm)
        }

        return root
    }

    private fun registerUser(username: String, password: String, confirm: String) {

        if (username == "" || username == " "){
            Toast.makeText(activity,"No pusiste un usuario", Toast.LENGTH_SHORT).show()
        }
        else if (password == "" || password == " "){
            Toast.makeText(activity,"No pusiste una contraseña", Toast.LENGTH_SHORT).show()
        }
        else if (confirm == "" || confirm == " "){
            Toast.makeText(activity,"No pusiste una confirmación de contraseña", Toast.LENGTH_SHORT).show()
        }
        else{
            if (password == confirm){
                // TODO: Parse verify username and password
                Toast.makeText(activity,"Registro exitoso", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(activity,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}