package com.itesm.esenciapatrimonio.ui

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.itesm.esenciapatrimonio.R
import com.itesm.esenciapatrimonio.databinding.FragmentMapBinding
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import org.json.JSONArray
import org.json.JSONObject

class MapFragment: Fragment() {
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    //components
    private var mapView: MapView? = null

    /**
     *Test Json array to markers
     */
    var strJson = ("{ \"Site\" :[{ \"lat\":\"20.693474\" , \"long\":\"-100.471939\",\"name\":\"Juriquilla\"}," +
                                "{\"lat\":\"20.612482937993608\" , \"long\":\"-100.40523130451855\",\"name\":\"Tec\"}] }")

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

            }
            //todo 1) syncronize with the data base
            //todo 2) for coordinate_x, coordinate_y in ResstoredSite table add a marker
            //todo 3) set an event for each marker onClick
            /**
             * Por cada sitio restaurado en la base de datos se agrega
             * un marcador poniendo las coordenadas guardadas, el nombre y el id
             */
            val jsonObject = JSONObject(strJson)
            val jsonArray = jsonObject.optJSONArray("Site")
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val lat = jsonObject.optString("lat").toDouble()
                val long = jsonObject.optString("long").toDouble()
                val name = jsonObject.optString("name").toString()
                mapboxMap.addMarker(
                    com.mapbox.mapboxsdk.annotations.MarkerOptions()
                        .position(com.mapbox.mapboxsdk.geometry.LatLng(lat, long))
                        .title(name)
                )
            }
        }

        return root
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