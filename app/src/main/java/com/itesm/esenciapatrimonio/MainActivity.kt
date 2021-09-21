package com.itesm.esenciapatrimonio

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.appbar.AppBarLayout
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.Style
import com.parse.Parse






class MainActivity : AppCompatActivity() {
    private var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))

        val oParse = ParseApp();
        //oParse.initParse();

        Parse.enableLocalDatastore(this)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id)) // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        )

        val SRestoreSite = oParse.getRestoreSite("oxLgAoPbTk");

        Log.d("Parse", "SiteName ${SRestoreSite.site_name}");
/*
        val firstObject = ParseObject("FirstClass")
        firstObject.put("message","Hey ! First message from android. Parse is now connected")
        firstObject.saveInBackground {
            if (it != null){
                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
            }else{
                Log.d("MainActivity","Object saved.")
            }
        }

        val ioObject = ParseObject("ioObject");
        ioObject.getInt("ioObject");


        val query = ParseQuery.getQuery<ParseObject>("GameScore")
        query.getInBackground("xWMyZ4YEGZ") { `object`, e ->
            if (e == null) {
                // object will be your game score
            } else {
                // something went wrong
            }
        }
*/
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync { mapboxMap ->

            mapboxMap.setStyle(Style.MAPBOX_STREETS) {

// Map is set up and the style has loaded. Now you can add data or make other map adjustments
            Log.d("salida","mapa cargado")

            }

        }
    }
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