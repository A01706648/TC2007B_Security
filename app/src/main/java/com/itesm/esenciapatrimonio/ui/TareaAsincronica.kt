package com.itesm.esenciapatrimonio.ui

import android.os.AsyncTask

abstract class TareaAsincrona(private val comunicacion: Comunicacion) :
    AsyncTask<Any?, Void?, Boolean>() {
    override fun onPreExecute() {
        comunicacion.toggleProgressBar(true)
    }

    protected override fun doInBackground(vararg p0: Any?): Boolean? {
        try {
            Thread.sleep(objects[2] as Int.toLong ())
            val user = objects[0] as String
            val pass = objects[1] as String
            return if (user == "admin" && pass == "admin") {
                true
            } else {
                false
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return false
    }

    override fun onPostExecute(bo: Boolean) {
        if (bo) {
            comunicacion.lanzarActividad(MapFragment::class.java)
            comunicacion.showMessage("Datos Correctos")
            comunicacion.toggleProgressBar(false)
        } else {
            comunicacion.showMessage("Datos Incorrectos")
        }
    }
}