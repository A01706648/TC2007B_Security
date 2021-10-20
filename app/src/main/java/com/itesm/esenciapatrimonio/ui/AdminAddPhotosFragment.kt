package com.itesm.esenciapatrimonio.ui

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.R
import com.itesm.esenciapatrimonio.databinding.FragmentAdminAddPhotosBinding

class AdminAddPhotosFragment: Fragment() {
    private var _binding: FragmentAdminAddPhotosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var triggerAntigua = 0
    var triggerActual = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdminAddPhotosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var code_id = 0

        _binding!!.imagenAntigua.setOnClickListener{
            code_id = 1
            pickImageGallery(code_id)
        }

        _binding!!.imagenActual.setOnClickListener{
            code_id = 2
            pickImageGallery(code_id)
        }

        _binding!!.imageUpload.setOnClickListener{
            // Revisa si entra el texto
            //Log.d(TAG, "Holaaaaaaa "+ _binding!!.tipoFoto.text.toString())
            if (triggerActual == 1 && triggerAntigua == 1 && _binding!!.tipoFoto.text.toString() != "") {
                //TODO: Code to send images and string to database





                _binding!!.imagenAntigua
                _binding!!.imagenActual

            }
            else{
                Toast.makeText(context, "Te falta poner algún dato", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    private fun pickImageGallery(code_id: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, code_id)
    }

    lateinit var imageNow:Uri
    lateinit var imageOld:Uri


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK){
            _binding!!.imagenAntigua.setImageURI(data?.data)
            triggerAntigua = 1

            imageOld = data?.data!!
        }
        if (requestCode == 2 && resultCode == RESULT_OK){
            _binding!!.imagenActual.setImageURI(data?.data)
            triggerActual = 1

            imageNow = data?.data!!
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
