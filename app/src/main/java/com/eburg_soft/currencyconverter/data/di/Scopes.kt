package com.eburg_soft.currencyconverter.data.di

import com.eburg_soft.currencyconverter.data.datasource.database.CurrencyConversionDatabase

object Scopes {
    const val APP = "scope.APP"
    const val CONVERTER = "scope.CONVERTER"
    const val HISTORY = "scope.HISTORY"
}