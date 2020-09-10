package com.eburg_soft.currencyconverter.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrenciesApi {

//    @GET("/latest?base=USD")
//    suspend fun getExchengeRates(): CurrencyConversionResponse

    @GET("/latest")
    suspend fun getExchengeRates(@Query("symbols", encoded = true) currencies: String): CurrencyConversionResponse
}