package com.eburg_soft.currencyconverter.core

import android.app.Application
import com.eburg_soft.currencyconverter.BuildConfig
import com.eburg_soft.currencyconverter.data.di.DatabaseModule
import com.eburg_soft.currencyconverter.data.di.NetworkModule
import com.eburg_soft.currencyconverter.data.di.RepositoryModule
import com.eburg_soft.currencyconverter.data.di.Scopes
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initToothpick()
        initTimber()

        Timber.d("App is created")
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) Configuration.forDevelopment().preventMultipleRootScopes() else Configuration.forProduction()

        Toothpick.openScope(Scopes.APP)
            .installModules(DatabaseModule(this), NetworkModule(), RepositoryModule())
    }
}