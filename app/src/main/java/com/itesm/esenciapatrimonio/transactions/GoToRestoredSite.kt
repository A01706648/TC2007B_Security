package com.itesm.esenciapatrimonio.transactions

import android.util.Log
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.R

/**
 * Recibe el fragmento a ser reemplazado y el fragmento que lo va a reemplazar
 * Se reemplazan mediante una transaccion en la parte de nav_host_fragment_content_main
 */
class GoToRestoredSite(fragment1: Fragment, fragment2: Fragment) {
    var oldFragment = fragment1
    var newFragment = fragment2

    fun makeTransaction(): Boolean {
        val fragmentManager = oldFragment.fragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, newFragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
        Log.d("mensaje", "no hace le commit")
        return true
    }
}