package com.eburg_soft.currencyconverter.data.datasource.network

import com.eburg_soft.currencyconverter.core.datatype.Result
import com.eburg_soft.currencyconverter.data.datasource.network.exceptions.handleNetworkExceptions
import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse
import javax.inject.Inject

class CurrencyConversionNetworkDataSource @Inject constructor(private val currenciesApi: CurrenciesApi) {

    suspend fun getExchangeRates(currencies: String): Result<CurrencyConversionResponse> {
        return try {
            val currencyConversionResponse = currenciesApi.getExchangeRates(currencies)
            Result.success(currencyConversionResponse)
        } catch (e: Exception) {
            Result.error(handleNetworkExceptions(e))
        }
    }
}