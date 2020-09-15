package com.eburg_soft.currencyconverter.features

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.eburg_soft.currencyconverter.R
import com.eburg_soft.currencyconverter.R.layout
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        initController()

        Timber.d("onCreate()")
    }

    private fun initController() {
        navController = Navigation.findNavController(this, R.id.container)
    }

    override fun onSupportNavigateUp(): Boolean = navController?.navigateUp() ?: false

//    override fun onBackPressed() {
//        val fragmentManager = supportFragmentManager
//        fragmentManager.findFragmentById(R.id.container)
//        if (fragmentManager.backStackEntryCount > 1) {
//            fragmentManager.popBackStack()
//        } else finish()
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }
}