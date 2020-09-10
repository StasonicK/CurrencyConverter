package com.eburg_soft.currencyconverter.features.history.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity

class CurrencyConversionsDiffCallback(
    private val oldList: List<CurrencyConversionEntity>,
    private val newList: List<CurrencyConversionEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrencyConversionEntity = oldList[oldItemPosition]
        val newCurrencyConversionEntity = newList[newItemPosition]
        return oldCurrencyConversionEntity.conversionId == newCurrencyConversionEntity.conversionId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrencyConversionEntity = oldList[oldItemPosition]
        val newCurrencyConversionEntity = newList[newItemPosition]
        return oldCurrencyConversionEntity.firstCurrencyNumber == newCurrencyConversionEntity.firstCurrencyNumber &&
                oldCurrencyConversionEntity.secondCurrencyNumber == newCurrencyConversionEntity.secondCurrencyNumber &&
                oldCurrencyConversionEntity.firstCurrencyType == newCurrencyConversionEntity.firstCurrencyType &&
                oldCurrencyConversionEntity.secondCurrencyType == newCurrencyConversionEntity.secondCurrencyType &&
                oldCurrencyConversionEntity.currenciesRate == newCurrencyConversionEntity.currenciesRate &&
                oldCurrencyConversionEntity.date == newCurrencyConversionEntity.date
    }
}