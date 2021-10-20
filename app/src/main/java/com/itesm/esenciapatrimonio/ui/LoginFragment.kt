package com.itesm.esenciapatrimonio.ui

/*import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.itesm.esenciapatrimonio.databinding.FragmentAboutUsBinding
import com.itesm.esenciapatrimonio.databinding.FragmentLoginMainBinding

const val RC_SIGN_IN = 123

class LoginFragment: Fragment() {
    private var _binding: FragmentLoginMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Aquí va todo el código

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(context, gso);

        binding.signInButton.visibility = View.VISIBLE
        binding.tvName.visibility = View.GONE
        binding.signInButton.setSize(SignInButton.SIZE_STANDARD)
        binding.signInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        val acct = GoogleSignIn.getLastSignedInAccount(context)
        if (acct != null) {
            binding.signInButton.visibility = View.GONE
            binding.tvName.text = acct.displayName
            binding.tvName.visibility = View.VISIBLE
        }

        // TODO: Establecer una cuenta estática que sólo puede acceder
        // Correo: esenciatestpatrimonio@gmail.com
        // Pass: M3m3h!123

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSingInResult(task)
        }
    }

    private fun handleSingInResult(completedTask: Task<GoogleSignInAccount>?) {
        try {
            val account = completedTask!!.getResult(ApiException::class.java)

            binding.signInButton.visibility = View.GONE
            binding.tvName.text = account.displayName
            binding.tvName.visibility = View.VISIBLE
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            binding.signInButton.visibility = View.VISIBLE
            binding.tvName.text = ""
            binding.tvName.visibility = View.GONE
        }
    }

}*/