package com.eburg_soft.currencyconverter.data.datasource.network

import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {

//    @GET("/latest?base=USD")
//    suspend fun getExchengeRates(): CurrencyConversionResponse?

    @GET("/latest")
    suspend fun getExchangeRates(@Query("symbols", encoded = true) currencies: String): CurrencyConversionResponse?
}