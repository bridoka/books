package com.ciandt.book.seeker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.setupActionBarWithNavController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navController = navHostFragment?.navController
        navController?.let {
            val toolbar: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)
            setupActionBarWithNavController(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController?.navigateUp()
        return true
    }
}
