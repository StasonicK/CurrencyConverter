package com.eburg_soft.currencyconverter.remote

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrenciesApi {

    @GET("latest?symbols={firstCurrency},{secondCurrency}")
    fun getExchengeRates(@Path("firstCurrency") firstCurrency:String, @Path("secondCurrency") secondCurrency:String):Deferred<Float>
}