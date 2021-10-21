package com.itesm.esenciapatrimonio.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.R
import com.itesm.esenciapatrimonio.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuario: EditText
    private lateinit var contrasena: EditText

    companion object {
        var isLogin: Boolean = false
    }

    var failedLoginAttempt = 0

    external fun getPassword(): String
    external fun getUsername(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        System.loadLibrary("keys")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This part works only for checking the login status
        if (!isLogin) {
            binding.botonLogin.setOnClickListener {
                val user: String = binding.Usuario.text.toString()
                val password: String = binding.contrasena.text.toString()

                login(user, password)
            }
        } else {
            // Go to the admin space
            Toast.makeText(activity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()

            val fragment = AdminMainFragment()

            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.commit()
        }
    }

    fun login(username: String, password: String) {

        if (username == "" || username == " ") {
            Toast.makeText(activity, "No pusiste un usuario", Toast.LENGTH_SHORT).show()
        } else if (password == "" || password == " ") {
            Toast.makeText(activity, "No pusiste una contraseña", Toast.LENGTH_SHORT).show()
        } else {
            // TODO: Vefify username and password but encrypted with something
            // All this data and checks are unencrypted / unsecure

            if (username.trim().equals(
                    getUsername(),
                    false
                ) && password.trim().equals(
                    getPassword(),
                    false
                )
            ) {
                // Global variable to don't login again while the app stays opened
                isLogin = true

                // Go to the admin space
                Toast.makeText(activity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val fragmentManager = fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()

                val fragment = AdminMainFragment()

                fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            } else {
                failedLoginAttempt++
                checkFailedLoginAttemptCase()
                Toast.makeText(activity, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun checkFailedLoginAttemptCase() {
        if (failedLoginAttempt >= 5) {
            failedLoginAttempt = 0

            binding.botonLogin.visibility = View.GONE
            binding.tvTimer.visibility = View.VISIBLE

            val timer = object : CountDownTimer(1000L * 60 * 5, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    var diff = millisUntilFinished
                    val secondsInMilli: Long = 1000
                    val minutesInMilli = secondsInMilli * 60

                    val elapsedMinutes = diff / minutesInMilli
                    diff %= minutesInMilli

                    val elapsedSeconds = diff / secondsInMilli

                    binding.tvTimer.text = "$elapsedMinutes:$elapsedSeconds"

                }

                override fun onFinish() {
                    binding.botonLogin.visibility = View.VISIBLE
                    binding.tvTimer.visibility = View.GONE
                }
            }
            timer.start()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}