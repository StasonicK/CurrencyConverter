package com.eburg_soft.currencyconverter.data.repository

import androidx.lifecycle.LiveData
import com.eburg_soft.currencyconverter.core.datatype.Result
import com.eburg_soft.currencyconverter.core.datatype.ResultType
import com.eburg_soft.currencyconverter.data.datasource.database.CurrencyConversionDatabase
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.datasource.network.CurrencyConversionNetworkDataSource
import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse
import javax.inject.Inject

class CurrencyConversionRepositoryImpl
@Inject constructor(
    private val currencyConversionDatabase: CurrencyConversionDatabase,
    private val currencyConversionNetworkDataSource: CurrencyConversionNetworkDataSource
) :
    CurrencyConversionRepository {

    private val currencyConversionDao = currencyConversionDatabase.currencyConversationDao()

    init {
//        Toothpick.inject(this, Toothpick.openScope(Scopes.APP))
    }

    override fun getAllCurrencyConversions(): LiveData<List<CurrencyConversionEntity>> =
        currencyConversionDao.getAllCurrencyConversions()

    override fun getSizeAllCurrencyConversions(): Int {
        return currencyConversionDao.getAllCurrencyConversions().value?.size ?: 0
    }

    override suspend fun saveCurrencyConversion(currencyConversionEntity: CurrencyConversionEntity) {
        currencyConversionDao.insertCurrencyConversion(currencyConversionEntity)
    }

    override suspend fun removeAllCurrenciesConversions() {
        currencyConversionDao.deleteAllCurrencyConversions()
    }

    override suspend fun getExchangeRates(currencies: String): Result<CurrencyConversionResponse> {
        currencyConversionNetworkDataSource.getExchangeRates(currencies).let { result ->
            return if (result.resultType != ResultType.ERROR) {
                result
            } else Result.error(result.error)
        }
    }
}