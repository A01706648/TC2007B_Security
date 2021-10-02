package com.itesm.esenciapatrimonio

import android.content.Context
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class Permissions() {
    /**
     * Permisos para la aplucación
     */

    fun requestPermissions(context: Context){
        val PERMISSION_ALL = 1
        val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )

        if (!hasPermit(context, *PERMISSIONS)) {
            ActivityCompat.requestPermissions(context as Activity, PERMISSIONS, PERMISSION_ALL)
        }
    }

    /**
     * Esta función verifica la existencia de todos los permisos para la aplicación.
     *
     * @param Context: Contexto de la aplicación
     * @param String: Arreglo de permisos a solicitar
     *
     * @return Boolean: Resultado de solicitud del permiso hasta que se cumpla todos los permisos
     */
    fun hasPermit(context: Context, vararg permissions: String): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }
}