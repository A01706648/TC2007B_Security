package com.itesm.esenciapatrimonio.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.parse.SignUpCallback
import com.parse.Parse
import com.parse.ParseException
import com.parse.ParseUser
import com.parse.LogInCallback


import com.itesm.esenciapatrimonio.R

import com.itesm.esenciapatrimonio.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuario: EditText
    private lateinit var contrasena: EditText


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
        binding.button3.setOnClickListener{
            val user:String = binding.Usuario.text.toString()
            val password:String = binding.contrasena.text.toString()

            login(user,password)
        }
    }




    fun login(username: String, password: String) {

        var cont:Int = 0

        val Puser:String = "user"
        val Ppassword:String = "password"

        if(username == ""){

            Toast.makeText(activity,"El usuario es requerido",Toast.LENGTH_SHORT).show()
        }else if(password == ""){

            Toast.makeText(activity,"La contraseña es requerida",Toast.LENGTH_SHORT).show()
        }else{

            ParseUser.logInInBackground(username,password,
                ({ user, e ->
                    if(user != null) {
                        //ir al map
                        Toast.makeText(activity, "Logeado con parse", Toast.LENGTH_LONG).show()
                        //val fragmentManager = fragmentManager
                        //val fragmentTransaction = fragmentManager?.beginTransaction()
                        //val fragment = MapFragment()

                    } else {
                        Toast.makeText(activity, "Contraseña y/o usuario equivocados parse", Toast.LENGTH_LONG).show()
                    }

                })
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}