package com.eburg_soft.currencyconverter.features.history.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity

class HistoryListViewModel : ViewModel() {

    private val currencyConversionListMutableLiveData: MutableLiveData<List<CurrencyConversionEntity>> =
        MutableLiveData()
    val currencyConversionListLiveData: LiveData<List<CurrencyConversionEntity>>
        get() = currencyConversionListMutableLiveData
}