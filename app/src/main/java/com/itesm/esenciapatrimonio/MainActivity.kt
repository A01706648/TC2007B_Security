package com.itesm.esenciapatrimonio

import android.os.Build
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.Style
import com.parse.Parse

class MainActivity : AppCompatActivity() {
    //mapBox
    private var mapView: MapView? = null
    //navigation view
    private lateinit var topAppBar: Toolbar
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navigationView:NavigationView

    fun ParseTest_GetRestoreSite(listRestoreSite:MutableList<SRestoreSite>):Unit
    {
        lateinit var oSite:SRestoreSite;

        for(oSite in listRestoreSite)
        {
            Log.d("Parse", "ObjectId ${oSite.objectId}");
            Log.d("Parse", "SiteName ${oSite.site_name}");
            Log.d("Parse", "X ${oSite.coordinate_x}");
            Log.d("Parse", "Y ${oSite.coordinate_y}");
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Mapbox
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))

        setContentView(R.layout.activity_main)
        /**
         * Drawer Layout
         **/
        topAppBar = findViewById(R.id.topAppBar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }

        /**
         * Mapbox
        **/
        mapView = findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync { mapboxMap ->

            mapboxMap.setStyle(Style.MAPBOX_STREETS) {

        // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                Log.d("salida","mapa cargado")

        val oParse = ParseApp();
        //oParse.initParse();

        Parse.enableLocalDatastore(this)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id)) // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                //.enableLocalDataStore()
                .build()
        )

        //oParse.getRestoreSite("oxLgAoPbTk", this::ParseTest_GetRestoreSite);
        oParse.getAllRestoreSite(this::ParseTest_GetRestoreSite);

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