package com.eburg_soft.currencyconverter.data.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import toothpick.Toothpick

object ToothpickViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Toothpick.openScope(Scopes.APP).getInstance(modelClass)
    }
}

class ToothpickViewModelFactoryScoped(private val scope: Any) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Toothpick.openScopes(Scopes.APP, scope).getInstance(modelClass)
    }
}