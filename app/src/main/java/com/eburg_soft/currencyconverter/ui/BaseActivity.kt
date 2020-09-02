package com.eburg_soft.currencyconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.eburg_soft.currencyconverter.R
import com.eburg_soft.currencyconverter.R.layout
import kotlinx.android.synthetic.main.activity_base.container

class BaseActivity : AppCompatActivity() {
    private lateinit var controller:NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_base)

        initController()
    }

    private fun initController() {
        controller = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment

    }
}