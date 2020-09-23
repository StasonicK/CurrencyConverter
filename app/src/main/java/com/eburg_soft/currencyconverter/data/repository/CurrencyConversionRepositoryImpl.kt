package com.eburg_soft.currencyconverter.data.repository

import androidx.lifecycle.LiveData
import com.eburg_soft.currencyconverter.core.datatype.Result
import com.eburg_soft.currencyconverter.core.datatype.ResultType
import com.eburg_soft.currencyconverter.data.datasource.database.daos.CurrencyConversionDao
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.datasource.network.CurrenciesNetworkDataSource
import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse
import javax.inject.Inject

class CurrencyConversionRepositoryImpl
@Inject constructor(
    private val currencyConversionDao: CurrencyConversionDao,
    private val currenciesNetworkDataSource: CurrenciesNetworkDataSource
) :
    CurrencyConversionRepository {

    override fun getAllCurrencyConversions(): LiveData<List<CurrencyConversionEntity>> =
        currencyConversionDao.getAllCurrencyConversions()

    override suspend fun saveCurrencyConversion(currencyConversionEntity: CurrencyConversionEntity) {
        currencyConversionDao.insertCurrencyConversion(currencyConversionEntity)
    }

    override suspend fun removeAllCurrenciesConversions() {
        currencyConversionDao.deleteAllCurrencyConversions()
    }

    override suspend fun remove(item: CurrencyConversionEntity) {
        currencyConversionDao.deleteCurrencyConversion(item)
    }

    override suspend fun getExchangeRates(currencies: String): Result<CurrencyConversionResponse> {
        currenciesNetworkDataSource.getExchangeRates(currencies).let { result ->
            return if (result.resultType != ResultType.ERROR) {
                result
            } else Result.error(result.error)

        }
    }
}