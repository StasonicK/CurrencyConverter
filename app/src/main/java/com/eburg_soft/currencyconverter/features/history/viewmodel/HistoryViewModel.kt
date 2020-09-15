package com.eburg_soft.currencyconverter.features.history.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.di.Scopes
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepository
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class HistoryViewModel @Inject constructor(private val currencyConversionRepository: CurrencyConversionRepository) :
    ViewModel() {

    private val currencyConversionListMediatorLiveData: MediatorLiveData<List<CurrencyConversionEntity>> =
        MediatorLiveData()
    val currencyConversionListLiveData: LiveData<List<CurrencyConversionEntity>>
        get() = currencyConversionListMediatorLiveData

    private val isLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: MutableLiveData<Boolean>
        get() = isLoadingMutableLiveData

    init {
        loadCurrencyConversions()
    }

    private fun loadCurrencyConversions() {
        isLoadingMutableLiveData.value = true
        val source = currencyConversionRepository.getAllCurrencyConversions()

        currencyConversionListMediatorLiveData.addSource(source) {
            if (it != null) {
                currencyConversionListMediatorLiveData.value = it
            }
            currencyConversionListMediatorLiveData.removeSource(source)
        }

        isLoadingMutableLiveData.value = false
    }

    // TODO: 10.09.2020 create method removeCurrencyConverion(item:Int)

    fun removeAllHistory() {
        viewModelScope.launch {
            currencyConversionRepository.removeAllCurrenciesConversions()
//            currencyConversionListMediatorLiveData.value = emptyList()
            currencyConversionListMediatorLiveData.postValue(emptyList())
        }
    }

    override fun onCleared() {
        super.onCleared()
        Toothpick.closeScope(Scopes.HISTORY)
    }
}