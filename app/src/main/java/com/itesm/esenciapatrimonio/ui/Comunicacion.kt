package com.itesm.esenciapatrimonio.ui

interface Comunicacion {
    fun toggleProgressBar(status: Boolean)
    fun lanzarActividad(tipoActividad: Class<*>?)
    fun showMessage(msg: String?)
}