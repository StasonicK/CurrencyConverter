package com.eburg_soft.currencyconverter.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.eburg_soft.currencyconverter.R
import com.eburg_soft.currencyconverter.R.layout
import com.eburg_soft.currencyconverter.data.di.Scopes
import toothpick.Toothpick

class BaseActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_base)

        initController()
    }

    private fun initController() {
//        navController = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = Navigation.findNavController(this, R.id.container)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

//    override fun onBackPressed() {
//        val fragmentManager = supportFragmentManager
//        fragmentManager.findFragmentById(R.id.container)
//        if (fragmentManager.backStackEntryCount > 1) {
//            fragmentManager.popBackStack()
//        } else finish()
//    }
}