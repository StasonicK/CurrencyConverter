package com.eburg_soft.currencyconverter.data.datasource.network

import com.eburg_soft.currencyconverter.core.datatype.Result
import com.eburg_soft.currencyconverter.data.datasource.network.exceptions.handleNetworkExceptions
import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse
import timber.log.Timber
import javax.inject.Inject

class CurrenciesNetworkDataSource @Inject constructor(private val currenciesApi: CurrenciesApi) {

    suspend fun getExchangeRates(currencies: String): Result<CurrencyConversionResponse> {
        return try {
            val currencyConversionResponse: CurrencyConversionResponse? = currenciesApi.getExchangeRates(currencies)
            Timber.d("$currencyConversionResponse")
            Result.success(currencyConversionResponse)
        } catch (e: Exception) {
            Result.error(handleNetworkExceptions(e))
        }
    }
}