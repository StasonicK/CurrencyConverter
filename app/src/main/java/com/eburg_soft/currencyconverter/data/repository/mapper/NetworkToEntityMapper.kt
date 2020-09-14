package com.eburg_soft.currencyconverter.data.repository.mapper

import com.eburg_soft.currencyconverter.core.BaseMapper
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse
import com.eburg_soft.currencyconverter.extensions.countCurrenciesRate
import com.eburg_soft.currencyconverter.extensions.round
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date

const val EXCEPTION_DATE = "Unparseable date"

//  Sample: "2020-09-03"
private const val recieveDatePattern = "yyyy-MM-dd"

//  Sample: "03.09.2020
private const val resultDatePattern = "dd.MM.yyyy"

class NetworkToEntityMapper : BaseMapper<CurrencyConversionResponse, CurrencyConversionEntity> {

    private var firstCurrencyNumber: Double = 0.0
    private var secondCurrencyNumber: Double = 0.0
    private var currenciesTypes: ArrayList<String> = arrayListOf()
    private var currenciesNumber: ArrayList<Double> = arrayListOf()

    fun setFirstCurrencyNumber(firstCurrencyNumber: Double) {
        this.firstCurrencyNumber = firstCurrencyNumber
        Timber.d("first currency number is set: %f", firstCurrencyNumber)
    }

    override fun map(type: CurrencyConversionResponse?): CurrencyConversionEntity {
        type?.rates?.forEach { (k, v) ->
            this.currenciesTypes.add(k)
            this.currenciesNumber.add(v)
        }
        Timber.d("%s to %f", currenciesTypes[0], currenciesNumber[0])
        Timber.d("currencies types size: %d, currencies number size: %d", currenciesTypes.size, currenciesNumber.size)

        if (currenciesTypes.size == 1) {
            currenciesTypes.add(currenciesTypes[0])
            currenciesNumber.add(currenciesNumber[0])
        }

        this.secondCurrencyNumber =
            countSecondCurrencyNumber(
                this.currenciesNumber[0],
                this.currenciesNumber[1],
                this.firstCurrencyNumber
            )
        Timber.d("second currency number is counted: %f", secondCurrencyNumber)

        val currenciesRate = this.currenciesNumber[0].countCurrenciesRate(this.currenciesNumber[1])
        val date = mapDate(type?.date.toString())
        return CurrencyConversionEntity(
            this.firstCurrencyNumber.round(2),
            this.currenciesTypes[1],
            this.secondCurrencyNumber.round(2),
            this.currenciesTypes[0],
            currenciesRate,
            date
        )
    }

    private fun countSecondCurrencyNumber(
        firstCurrencyRate: Double,
        secondCurrencyRate: Double,
        firstCurrencyNumber: Double
    ): Double = firstCurrencyNumber * (firstCurrencyRate / secondCurrencyRate)

    @Throws(Exception::class)
    private fun mapDate(date: String): String {
        var simpleDate: Date? = null
        try {
            simpleDate = SimpleDateFormat(recieveDatePattern).parse(date)
        } catch (e: Exception) {
            throw Exception(EXCEPTION_DATE)
        }
        Timber.d("date: %s", simpleDate.toString())
        return SimpleDateFormat(resultDatePattern).format(simpleDate)
    }
}