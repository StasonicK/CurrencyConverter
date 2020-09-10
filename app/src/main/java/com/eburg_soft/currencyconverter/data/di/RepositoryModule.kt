package com.eburg_soft.currencyconverter.data.di

import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepository
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepositoryImpl
import toothpick.config.Module

class RepositoryModule : Module() {

    init {
        bind(CurrencyConversionRepository::class.java)
            .to(CurrencyConversionRepositoryImpl::class.java)
            .singleton()
    }
}