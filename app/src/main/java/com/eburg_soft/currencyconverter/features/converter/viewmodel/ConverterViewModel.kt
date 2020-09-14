package com.eburg_soft.currencyconverter.features.converter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eburg_soft.currencyconverter.core.datatype.ResultType
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.di.Scopes
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepository
import com.eburg_soft.currencyconverter.data.repository.mapper.NetworkToEntityMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class ConverterViewModel
@Inject constructor(private val currencyConversionRepository: CurrencyConversionRepository) :
    ViewModel() {

    private val secondCurrenciesNumberMutableLiveData: MutableLiveData<Double> = MutableLiveData()
    val secondCurrenciesNumberLiveData: MutableLiveData<Double>
        get() = secondCurrenciesNumberMutableLiveData

    private val isErrorOnLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorOnLoadingLiveData: MutableLiveData<Boolean>
        get() = isErrorOnLoadingMutableLiveData

    private val isLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: MutableLiveData<Boolean>
        get() = isLoadingMutableLiveData

    private val isExistingHistoryMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isExistingHistorySizeLiveData: LiveData<Boolean>
        get() = isExistingHistoryMutableLiveData

    init {
//        Toothpick.inject(this, Toothpick.openScope(Scopes.CONVERTER))
        checkHistorySize()
    }

    private fun checkHistorySize() {
        viewModelScope.launch {
            if (currencyConversionRepository.getSizeAllCurrencyConversions() > 0) {
                isExistingHistoryMutableLiveData.postValue(true)
            } else {
                isExistingHistoryMutableLiveData.postValue(false)
            }
        }
    }

    /*
        save current currency conversion:
        1. get response from network;
            2. if result is success:
                2.1. map response data to entity object;
                2.2. save new currency conversion to database;
                2.3. get second currency number to UI;
            else:
                2.1. show error in AlertDialog.
     */
    fun saveCurrencyConversion(
        firstCurrencyNumber: String,
        firstCurrenciesType: String,
        secondCurrenciesType: String
    ) {
        viewModelScope.launch {
            if (firstCurrenciesType == secondCurrenciesType) {
                secondCurrenciesNumberMutableLiveData.value = firstCurrencyNumber.toDouble()
                return@launch
            }
            val networkToEntityMapper = NetworkToEntityMapper()
            networkToEntityMapper.setFirstCurrencyNumber(firstCurrencyNumber = firstCurrencyNumber.toDouble())
            val currencies = "$firstCurrenciesType,$secondCurrenciesType"
            val currencyConversionResult = currencyConversionRepository.getExchangeRates(currencies)
            if (isResultSuccess(currencyConversionResult.resultType)) {
                onResultSuccess(networkToEntityMapper.map(currencyConversionResult.data))
            } else {
                onResultError()
            }

        }
    }

    private fun onResultError() {
        viewModelScope.launch {
            delay(300)
            isLoadingMutableLiveData.value = false
        }.invokeOnCompletion {
            isErrorOnLoadingMutableLiveData.value = false
        }
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultSuccess(currencyConversionEntity: CurrencyConversionEntity?) {
        viewModelScope.launch {
            currencyConversionEntity?.let {
                currencyConversionRepository.saveCurrencyConversion(it)
                secondCurrenciesNumberMutableLiveData.postValue(it.secondCurrencyNumber)
            }
            checkHistorySize()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Toothpick.closeScope(Scopes.CONVERTER)
    }
}