package com.eburg_soft.currencyconverter.data.repository.mapper

import com.eburg_soft.currencyconverter.core.BaseMapper
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse
import com.eburg_soft.currencyconverter.extensions.countFirstCurrencyToSecondCurrencyRate
import com.eburg_soft.currencyconverter.extensions.round
import java.text.SimpleDateFormat
import java.util.Date

const val EXCEPTION_DATE = "Unparseable date"

//  Sample: "2020-09-03"
private const val recieveDatePattern = "yyyy-MM-dd"

//  Sample: "03.09.2020
private const val resultDatePattern = "dd.MM.yyyy"

object NetworkToEntityMapper : BaseMapper<CurrencyConversionResponse, CurrencyConversionEntity> {

    private var firstCurrencyNumber: Double = 0.0
    private var secondCurrencyNumber: Double = 0.0
    private var currenciesTypes: ArrayList<String> = arrayListOf()
    private var currenciesNumber: ArrayList<Double> = arrayListOf()

    fun setFirstCurrencyNumber(firstCurrencyNumber: Double) {
        this.firstCurrencyNumber = firstCurrencyNumber
    }

    override fun map(type: CurrencyConversionResponse?): CurrencyConversionEntity {
        type?.rates?.forEach { (k, v) ->
            currenciesTypes.add(k)
            currenciesNumber.add(v)
        }
        secondCurrencyNumber =
            countSecondCurrencyNumber(currenciesNumber[0], currenciesNumber[1], firstCurrencyNumber)
        return CurrencyConversionEntity(
            firstCurrencyNumber.round(2),
            currenciesTypes[0],
            secondCurrencyNumber.round(2),
            currenciesTypes[1],
            currenciesNumber[0].countFirstCurrencyToSecondCurrencyRate(currenciesNumber[1]),
            mapDate(type?.date.toString())
        )
    }

    private fun countSecondCurrencyNumber(
        firstCurrencyRate: Double,
        secondCurrencyRate: Double,
        firstCurrencyNumber: Double
    ): Double = firstCurrencyNumber * (secondCurrencyRate / firstCurrencyRate)

    @Throws(Exception::class)
    private fun mapDate(date: String): String {
        var simpleDate: Date? = null
        try {
            simpleDate = SimpleDateFormat(recieveDatePattern).parse(date)
        } catch (e: Exception) {
            throw Exception(EXCEPTION_DATE)
        }
        return SimpleDateFormat(resultDatePattern).format(simpleDate)
    }
}