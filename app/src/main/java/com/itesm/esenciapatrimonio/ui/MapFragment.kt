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

class MapFragment: Fragment() {
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    //components
    private var mapView: MapView? = null

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
            mapboxMap.addMarker(com.mapbox.mapboxsdk.annotations.MarkerOptions()
                .position(com.mapbox.mapboxsdk.geometry.LatLng(20.693474, -100.471939))
                .title("Juriquilla QRO"))
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