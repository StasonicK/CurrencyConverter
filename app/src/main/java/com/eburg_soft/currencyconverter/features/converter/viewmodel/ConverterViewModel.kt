package com.eburg_soft.currencyconverter.features.converter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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
import retrofit2.HttpException
import toothpick.Toothpick
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class ConverterViewModel @Inject constructor(private val currencyConversionRepository: CurrencyConversionRepository) :
    ViewModel() {

    private val secondCurrenciesNumberMutableLiveData: MutableLiveData<Double> = MutableLiveData()
    val secondCurrenciesNumberLiveData: MutableLiveData<Double>
        get() = secondCurrenciesNumberMutableLiveData

    private val errorOnLoadingMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val errorOnLoadingLiveData: MutableLiveData<String>
        get() = errorOnLoadingMutableLiveData

    private val isLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: MutableLiveData<Boolean>
        get() = isLoadingMutableLiveData

    private val isExistingHistoryMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isExistingHistoryLiveData: LiveData<Boolean>
        get() = isExistingHistoryMutableLiveData

    init {
//        checkHistorySize()
    }

    private fun checkHistorySize() {
//        viewModelScope.launch {
        var currencyConversionListMediatorLiveData: MediatorLiveData<List<CurrencyConversionEntity>> =
            MediatorLiveData()
        val source = currencyConversionRepository.getAllCurrencyConversions()

        currencyConversionListMediatorLiveData.addSource(source) {
            if (it != null) {
                currencyConversionListMediatorLiveData.value = it
            }
            currencyConversionListMediatorLiveData.removeSource(source)
        }

//        val liveData = currencyConversionRepository.getAllCurrencyConversions()
        val value = currencyConversionListMediatorLiveData.value
        val size = value?.size
        isExistingHistoryMutableLiveData.value = size!! > 0
//        }
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
    // TODO: 15.09.2020 refactor! 
    fun saveCurrencyConversion(
        firstCurrencyNumber: String,
        firstCurrenciesType: String,
        secondCurrenciesType: String
    ) {
        viewModelScope.launch {
            val currencyConversionEntity: CurrencyConversionEntity

            isLoadingMutableLiveData.value = true

            if (firstCurrenciesType != secondCurrenciesType) {
                val networkToEntityMapper = NetworkToEntityMapper()
                networkToEntityMapper.setFirstCurrencyNumber(firstCurrencyNumber = firstCurrencyNumber.toDouble())
                val currencies = "$firstCurrenciesType,$secondCurrenciesType"

                val currencyConversionResult = currencyConversionRepository.getExchangeRates(currencies)

                if (isResultSuccess(currencyConversionResult.resultType)) {
                    currencyConversionEntity = networkToEntityMapper.map(currencyConversionResult.data)
                    onResultSuccess(currencyConversionEntity)
                    secondCurrenciesNumberMutableLiveData.postValue(currencyConversionEntity.secondCurrencyNumber)
                } else {
                    onResultError(currencyConversionResult.error)
                }
            } else {
                currencyConversionEntity = CurrencyConversionEntity(
                    firstCurrencyNumber.toDouble(),
                    firstCurrenciesType,
                    firstCurrencyNumber.toDouble(),
                    secondCurrenciesType, date = getCurrentDate()
                )
                currencyConversionRepository.saveCurrencyConversion(
                    currencyConversionEntity
                )
                secondCurrenciesNumberMutableLiveData.value = firstCurrencyNumber.toDouble()
            }
            delay(300)
            isLoadingMutableLiveData.value = false
        }
//        checkHistorySize()
    }

    private fun onResultError(exception: Exception?) {
        viewModelScope.launch {

            isLoadingMutableLiveData.value = false
        }.invokeOnCompletion {
            if (exception is HttpException)
                exception.let {
                    errorOnLoadingMutableLiveData.value = "Currencies are invalid for current date"
                }
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
        }
    }

    private fun getCurrentDate() =
        SimpleDateFormat(
            NetworkToEntityMapper.resultDatePattern,
            Locale.getDefault()
        ).format(Calendar.getInstance().time)

    override fun onCleared() {
        super.onCleared()
        Toothpick.closeScope(Scopes.CONVERTER)
    }
}