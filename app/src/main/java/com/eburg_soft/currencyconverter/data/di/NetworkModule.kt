package com.eburg_soft.currencyconverter.data.di

import com.eburg_soft.currencyconverter.data.datasource.network.CurrenciesApi
import com.eburg_soft.currencyconverter.data.datasource.network.CurrenciesApiService
import toothpick.config.Module

class NetworkModule : Module() {
    init {
        bind(CurrenciesApi::class.java)
            .toInstance(CurrenciesApiService.currencyApi())
    }
}