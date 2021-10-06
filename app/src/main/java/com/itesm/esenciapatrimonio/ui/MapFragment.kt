package com.itesm.esenciapatrimonio.ui

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.itesm.esenciapatrimonio.ParseApp
import com.itesm.esenciapatrimonio.R
import com.itesm.esenciapatrimonio.SRestoreSite
import com.itesm.esenciapatrimonio.databinding.FragmentMapBinding
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import org.json.JSONArray
import org.json.JSONObject

class MapFragment: Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    //components
    private var mapView: MapView? = null
    private var parseCallbackUse_MapboxMap: MapboxMap? = null

    //permissions

    //restored site array from database
    lateinit var restoredSite: MutableList<SRestoreSite>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))

        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //query
        restoredSite = ArrayList()
        //getRestoredSites()

        mapView = binding.mapView
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync { mapboxMap ->
            mapboxMap.setStyle(getString(R.string.map_style)) {
                // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                /*
                * Si el permiso de acceso a la ubicación es true
                *  Mostrar el mapa desde la ubicación del usuario
                 */
                //if (PermissionsManager.areLocationPermissionsGranted(this))
                //todo 3) for coordinate_x, coordinate_y in ResstoredSite table add a marker
                //todo 4) set an event for each marker onClick
                /*
                 * Por cada sitio restaurado en la base de datos se agrega
                 * un marcador poniendo las coordenadas guardadas, el nombre y el id
                 */
                this.parseCallbackUse_MapboxMap = mapboxMap
                val oParse = ParseApp();
                oParse.getAllRestoreSite(this::ParseTest_GetRestoreSite)
            }

        }

        return root
    }



    fun ParseTest_GetRestoreSite(listRestoreSite:MutableList<SRestoreSite>):Unit{
        //Log.d("Parse", "restoredSite ${listRestoreSite.size}")
        restoredSite = listRestoreSite
        for (i in 0 until listRestoreSite.size) {
            val Site = restoredSite[i]
            Log.d("Lat and long", "xCoord, yCoord ${Site.coordinate_x}, ${Site.coordinate_y}")
            this.parseCallbackUse_MapboxMap?.addMarker(
                com.mapbox.mapboxsdk.annotations.MarkerOptions()
                    .position(com.mapbox.mapboxsdk.geometry.LatLng(Site.coordinate_x, Site.coordinate_y))
                    .title(Site.site_name)
            )
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