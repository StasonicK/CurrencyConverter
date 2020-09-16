package com.eburg_soft.currencyconverter.features.history.ui.adapter

import android.util.SparseIntArray
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eburg_soft.currencyconverter.R
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.extensions.inflate
import com.eburg_soft.currencyconverter.features.history.ui.adapter.CurrencyConversionAdapter.CurrencyConversionHolder
import kotlinx.android.synthetic.main.item_list_currency_conversion.view.tvCurrenciesRate
import kotlinx.android.synthetic.main.item_list_currency_conversion.view.tvDate
import kotlinx.android.synthetic.main.item_list_currency_conversion.view.tvFirstCurrencyNumber
import kotlinx.android.synthetic.main.item_list_currency_conversion.view.tvFirstCurrencyType
import kotlinx.android.synthetic.main.item_list_currency_conversion.view.tvSecondCurrencyNumber
import kotlinx.android.synthetic.main.item_list_currency_conversion.view.tvSecondCurrencyType

class CurrencyConversionAdapter : RecyclerView.Adapter<CurrencyConversionHolder>() {

    private lateinit var currencyConversions: List<CurrencyConversionEntity>

    class CurrencyConversionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(currencyConversionEntity: CurrencyConversionEntity) {
            itemView.apply {
                tvFirstCurrencyNumber.text = currencyConversionEntity.firstCurrencyNumber.toString()
                tvSecondCurrencyNumber.text = currencyConversionEntity.secondCurrencyNumber.toString()
                tvFirstCurrencyType.text = currencyConversionEntity.firstCurrencyType.toString()
                tvSecondCurrencyType.text = currencyConversionEntity.secondCurrencyType.toString()
                tvCurrenciesRate.text = currencyConversionEntity.currenciesRate.toString()
                tvDate.text = currencyConversionEntity.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyConversionHolder {
        val view = parent.inflate(R.layout.item_list_currency_conversion)
        return CurrencyConversionHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyConversionHolder, position: Int) {
        holder.apply {
            bind(currencyConversions[position])
        }
    }

    override fun getItemCount(): Int = currencyConversions.size

    fun updateAdapter(updatedList: List<CurrencyConversionEntity>) {
        val result = DiffUtil.calculateDiff(CurrencyConversionsDiffCallback(currencyConversions, updatedList))
        this.currencyConversions = updatedList.toMutableList()
        result.dispatchUpdatesTo(this)
    }

    fun setData(currencyConversions: List<CurrencyConversionEntity>) {
        this.currencyConversions = currencyConversions
    }
}

