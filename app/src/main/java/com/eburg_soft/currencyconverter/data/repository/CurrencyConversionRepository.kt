package com.eburg_soft.currencyconverter.data.repository

import androidx.lifecycle.LiveData
import com.eburg_soft.currencyconverter.core.datatype.Result
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse

interface CurrencyConversionRepository {

    fun getAllCurrencyConversions(): LiveData<List<CurrencyConversionEntity>>
//    fun getSizeCurrencyConversionList(): Int

    suspend fun saveCurrencyConversion(currencyConversionEntity: CurrencyConversionEntity)
    suspend fun removeAllCurrenciesConversions()
    suspend fun getExchangeRates(currencies: String): Result<CurrencyConversionResponse>
}