package com.sparklead.evocharge.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.sparklead.evocharge.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.app_bar_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity(Intent(this,ReportActivity::class.java))
        }


        val drawerLayout: DrawerLayout = drawer_layout
        val navView: NavigationView = nav_view
        val navController = findNavController(R.id.nav_host_fragment_content_dashboard)


        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_Dashboard, R.id.nav_History, R.id.nav_report
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val drawerLayout: DrawerLayout = drawer_layout
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_Dashboard, R.id.nav_History, R.id.nav_report
            ), drawerLayout
        )
        val navController = findNavController(R.id.nav_host_fragment_content_dashboard)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}