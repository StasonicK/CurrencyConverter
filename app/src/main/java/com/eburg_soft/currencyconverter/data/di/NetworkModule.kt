package com.eburg_soft.currencyconverter.data.datasource.network

import toothpick.config.Module

object NetworkModule : Module() {
    init {
        bind(CurrenciesApi::class.java)
            .to(ServiceGenerator().currencyApi()::class.java)
            .singleton()
    }
}