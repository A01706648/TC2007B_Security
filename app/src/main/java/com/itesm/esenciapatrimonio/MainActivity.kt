package com.itesm.esenciapatrimonio

import android.os.Build
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.AppBarLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.itesm.esenciapatrimonio.databinding.ActivityMainBinding
import com.itesm.esenciapatrimonio.Permissions
import com.parse.Parse

class MainActivity : AppCompatActivity() {

    //navigation view
    private lateinit var topAppBar: Toolbar
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navigationView:NavigationView

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    var context: Context = this

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

    /**
     * Android onCreate
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Solicitar permisos
        val permissions: Permissions = Permissions()
        permissions.requestPermissions(context)

        //Iniciar el menu lateral
        setSupportActionBar(binding.appBarMain.topAppBar)
        initializeNavbar()
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
                R.id.view_map, R.id.view_advanced_search, R.id.view_about_us
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
}