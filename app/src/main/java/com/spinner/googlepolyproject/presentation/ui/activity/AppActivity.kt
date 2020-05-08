package com.spinner.googlepolyproject.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.spinner.googlepolyproject.R
import kotlinx.android.synthetic.main.activity_main.*

class AppActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        //toolbar.setupWithNavController(navController, appBarConfiguration)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
