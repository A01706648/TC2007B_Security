package com.itesm.esenciapatrimonio.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.itesm.esenciapatrimonio.ParseApp
import com.itesm.esenciapatrimonio.R
import com.itesm.esenciapatrimonio.SRestoreSite
import com.itesm.esenciapatrimonio.transactions.TransactionData
import com.itesm.esenciapatrimonio.databinding.FragmentMapBinding
import com.itesm.esenciapatrimonio.transactions.GoToRestoredSite
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap

class MapFragment: Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    //components
    private var mapView: MapView? = null
    private var parseCallbackUse_MapboxMap: MapboxMap? = null

    //restored site array from database
    lateinit var restoredSite: MutableList<SRestoreSite>
    lateinit var restoredSiteMarkers: MutableList<Marker>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))

        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mapView = binding.mapView
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync { mapboxMap ->
            mapboxMap.setStyle(getString(R.string.map_style)) {
                // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                val locationOne = LatLng(20.76853263116804, -100.46317287806771)
                val locationTwo = LatLng(20.49847887794351, -100.35621872052026)

                val latLngBounds = LatLngBounds.Builder()
                    .include(locationOne)
                    .include(locationTwo)
                    .build()
                mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));

                // Llama a Parse para cargar los datos
                this.parseCallbackUse_MapboxMap = mapboxMap
                //val oParse = ParseApp();

                //Verificar que el dispositivo este conectado a internet o utilizando datos
                val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
                if (activeNetwork != null && activeNetwork.isConnected) {
                    //Si el dispositivo esta conectado carga los datos de parse en el mapa
                    ParseApp.getAllRestoreSite(this::ParseTest_GetRestoreSite)
                }else {
                    Toast.makeText(context,"Error de conexión, verifique que su dispositivo esté conectado a internet", Toast.LENGTH_SHORT).show()
                }

                //comparar de la lista de marcadores el marcador seleccionado
                //obteniendo el indice llevar al sitio correspondiente
                mapboxMap.setOnMarkerClickListener { marker ->
                    var index: Int = 0

                    for (i in 0 until restoredSiteMarkers.size) {
                        if (marker.title == restoredSiteMarkers[i].title) {
                            index = i
                        }
                    }
                    //una vez obtenido el indice se lleva a la vista del sitio restaurado correspondiente
                    //los datos son guardados en un objeto TransactionData para utilizarlos en el constructor del sitio restaurado
                    TransactionData.restoredSite = mutableListOf(restoredSite[index])
                    GoToRestoredSite(this, RestoredSiteFragment()).makeTransaction()
                }

            }

        }

        return root
    }

    /*
     * Por cada sitio restaurado en la base de datos se agrega
     * un marcador poniendo las coordenadas guardadas, el nombre y el id
     */
    fun ParseTest_GetRestoreSite(listRestoreSite:MutableList<SRestoreSite>):Unit{
        restoredSite = listRestoreSite
        restoredSiteMarkers = mutableListOf()
        for (i in 0 until listRestoreSite.size) {
            val Site = restoredSite[i]
            val marker = com.mapbox.mapboxsdk.annotations.MarkerOptions()
                .position(
                    com.mapbox.mapboxsdk.geometry.LatLng(
                        Site.coordinate_x,
                        Site.coordinate_y
                    )
                )
                .title(Site.site_name)
            this.parseCallbackUse_MapboxMap?.addMarker(marker)
            restoredSiteMarkers.add(marker.marker)
        }
    }

    /**
     * Map Lidecycle Methods
     */
    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
}

