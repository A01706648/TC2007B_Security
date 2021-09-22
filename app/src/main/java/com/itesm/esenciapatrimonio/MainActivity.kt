package com.itesm.esenciapatrimonio

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.itesm.esenciapatrimonio.databinding.ActivityMainBinding
import com.mapbox.mapboxsdk.maps.MapView
<<<<<<< HEAD
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style

class MainActivity : AppCompatActivity() {
    //mapBox
    private var mapView: MapView? = null
    private var mapboxMap: MapboxMap? = null
=======

class MainActivity : AppCompatActivity() {
    //mapBox


>>>>>>> 41c5237347e8936e22e073589f7d05c763d30e6b
    //navigation view
    private lateinit var topAppBar: Toolbar
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navigationView:NavigationView

<<<<<<< HEAD
=======
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

>>>>>>> 41c5237347e8936e22e073589f7d05c763d30e6b
    /**
     * Android onCreate
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Mapbox

<<<<<<< HEAD
        setContentView(R.layout.activity_main)
        initializeComponents()

        /**
         * Mapbox initialize
         **/
        mapView = findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync { mapboxMap ->

            mapboxMap.setStyle(getString(R.string.map_style)) {
                // Map is set up and the style has loaded. Now you can add data or make other map adjustments

            }

        }
    }

    /**
     * Drawer Layout
     **/
    private fun initializeComponents(){
        topAppBar = findViewById(R.id.topAppBar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        //compact activity

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true

            //R.id.view_acercaDeNosotros -> { setContentView(R.layout.about_us) }

            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }

    }

    /**
     * Mapbox components
     */
    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
=======
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.topAppBar)
        initializeNavbar()
        /**
         * Mapbox initialize
         **/
>>>>>>> 41c5237347e8936e22e073589f7d05c763d30e6b
    }

    /**
     * Drawer Layout
     **/
    private fun initializeNavbar(){
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navigationView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.view_map, R.id.view_about_us
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.lateral_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * Mapbox components
     */
    /*

    */
}