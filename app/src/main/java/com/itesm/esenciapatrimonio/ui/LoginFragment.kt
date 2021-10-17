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

//<<<<<<< HEAD
import com.itesm.esenciapatrimonio.R
//import com.itesm.esenciapatrimonio.databinding.FragmentAdvancedSearchBinding
//=======
//>>>>>>> 8cc3df98f71052e06bf3a946e926cc84431da525
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

    //override fun onViewCreate(view: View, savedInstanceState: Bundle?){
    //    super.onViewCreate(view, savedInstanceState)
    //    binding.button3.setOnClickListener{
    //        login("user","password")
    //    }
    //}


/*
    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                dialog.cancel()
                // don't forget to change the line below with the names of your Activities
                val intent = Intent(this, LoginFragment::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        val ok = builder.create()
        ok.show()
    }
*/
    fun login(username: String, password: String) {

        var cont:Int = 0

        val Puser:String = "user"
        val Ppassword:String = "password"

        if(!TextUtils.isEmpty(username)){
            usuario.setError("El nombre del usuario es requerido")
        }else if(!TextUtils.isEmpty(password)){
            contrasena.setError("La contraseña es requerida")
        }else{/*
            //progressDialog?.show()
            ParseUser.logInInBackground(user,password) { parseUser:
            ParseUser?, parseException: ParseException? ->
                //progressDialog?.dismiss()
                if (parseUser != null) {
                    //showAlert("Successful Login", "Welcome back " + user + " !")
                } else {
                    //ParseUser.logOut()
                    if (parseException != null) {
                        Toast.makeText(this, parseException.message, Toast.LENGTH_LONG).show()
                    }
                }
            }*/

            //if(Puser == username && Ppassword == password){
            //    goToMapView(cont)

            //}else{
            //    cont++
            //}
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}