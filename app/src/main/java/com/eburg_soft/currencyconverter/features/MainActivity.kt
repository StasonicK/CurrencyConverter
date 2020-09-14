package com.eburg_soft.currencyconverter.features

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.eburg_soft.currencyconverter.R
import com.eburg_soft.currencyconverter.R.layout
import kotlinx.android.synthetic.main.activity_main.toolbarMainActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        initUI()
        initController()

        Timber.d("onCreate()")
    }

    private fun initUI() {
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbarMainActivity as Toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initController() {
        val navController = Navigation.findNavController(this, R.id.container)
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.container).navigateUp()

//    override fun onBackPressed() {
//        val fragmentManager = supportFragmentManager
//        fragmentManager.findFragmentById(R.id.container)
//        if (fragmentManager.backStackEntryCount > 1) {
//            fragmentManager.popBackStack()
//        } else finish()
//    }

    override fun onStop() {
        super.onStop()
    }
}