package com.eburg_soft.currencyconverter.features.history.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.di.Scopes
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepository
import toothpick.Toothpick
import javax.inject.Inject

class HistoryViewModel @Inject constructor(private val currencyConversionRepository: CurrencyConversionRepository) :
    ViewModel() {

//    @Inject
//    private lateinit var currencyConversionRepository: CurrencyConversionRepository


    private val currencyConversionListMediatorLiveData: MediatorLiveData<List<CurrencyConversionEntity>> =
        MediatorLiveData()
    val currencyConversionListLiveData: LiveData<List<CurrencyConversionEntity>>
        get() = currencyConversionListMediatorLiveData

    private val isLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: MutableLiveData<Boolean>
        get() = isLoadingMutableLiveData

    init {
//        Toothpick.inject(this, Toothpick.openScope(Scopes.HISTORY))

        loadCurrencyConversions()
    }

    private fun loadCurrencyConversions() {
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

    // TODO: 10.09.2020 create method removeAllCurrencyConverions()

    override fun onCleared() {
        super.onCleared()
        Toothpick.closeScope(Scopes.HISTORY)
    }
}