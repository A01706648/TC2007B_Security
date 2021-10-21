package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.R
import com.itesm.esenciapatrimonio.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuario: EditText
    private lateinit var contrasena: EditText

    companion object{
        var isLogin:Boolean = false
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        if(!isLogin) {
            binding.botonLogin.setOnClickListener {
                val user: String = binding.Usuario.text.toString()
                val password: String = binding.contrasena.text.toString()

                login(user, password)
            }
        }
        else{
            Toast.makeText(activity,"Inicio de sesión exitoso",Toast.LENGTH_SHORT).show()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()

            val fragment = AdminMainFragment()

            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.commit()
        }
    }

    fun login(username: String, password: String) {

        if (username == "" || username == " "){
            Toast.makeText(activity,"No pusiste un usuario",Toast.LENGTH_SHORT).show()
        }
        else if (password == "" || password == " "){
            Toast.makeText(activity,"No pusiste una contraseña",Toast.LENGTH_SHORT).show()
        }
        else{
        // TODO: Parse verify username and password
            if(username == "patrimonio" && password == "1234"){
                isLogin = true

                Toast.makeText(activity,"Inicio de sesión exitoso",Toast.LENGTH_SHORT).show()
                val fragmentManager = fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()

                val fragment = AdminMainFragment()

                fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            }
            else{
                Toast.makeText(activity,"Usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}