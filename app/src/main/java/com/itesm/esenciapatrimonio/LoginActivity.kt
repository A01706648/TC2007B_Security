package com.itesm.esenciapatrimonio

import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.LogInCallback
import com.parse.ParseException
import com.parse.ParseUser

class LoginActivity: AppCompatActivity {

    private lateinit var usuario: EditText
    private lateinit var contrasena: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

        usuario = findViewById(R.id.Usuario)
        contrasena = findViewById(R.id.contrasena)
    }

    private fun loginUser(){
        val user:String = usuario.text.toString()
        val password:String = contrasena.text.toString()

        if(!TextUtils.isEmpty(user)){
            usuario.setError("El nombre del usuario es requerido")
        }else if(!TextUtils.isEmpty(password)){
            contrasena.setError("La contraseña es requerida")
        }else{
            //progressDialog?.show()
            ParseUser.logInInBackground(user,password) { parseUser: ParseUser?, parseException: ParseException? ->
                //progressDialog?.dismiss()
                if (parseUser != null) {
                    //showAlert("Successful Login", "Welcome back " + user + " !")
                } else {
                    //ParseUser.logOut()
                    if (parseException != null) {
                        Toast.makeText(this, parseException.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}