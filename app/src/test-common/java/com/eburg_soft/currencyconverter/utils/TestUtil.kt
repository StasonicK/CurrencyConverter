package com.eburg_soft.currencyconverter.utils

import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse
import java.util.Collections

object TestUtil {

    val CURRENCY_CONVERSION_ONE = CurrencyConversionEntity(1.0, "USD", 1.32, "CAD", 1.3154, "10.09.2020")
    val CURRENCY_CONVERSION_TWO = CurrencyConversionEntity(1.0, "USD", 7.75, "HKD", 0.1290, "10.09.2020")
    val CURRENCY_CONVERSION_EQUAL_TYPES = CurrencyConversionEntity(1.0, "USD", 1.0, "USD", 1.0, "10.09.2020")

    val CURRENCY_CONVERSION_RES_ONE = CurrencyConversionResponse(
        hashMapOf("CAD" to 1.5586, "USD" to 1.1876), "EUR", "2020-09-10"
    )

    val CURRENCY_CONVERSION_RES_TWO = CurrencyConversionResponse(
        hashMapOf("HKD" to 9.1834, "USD" to 1.1876), "EUR", "2020-09-10"
    )

    val CURRENCY_CONVERSION_RES_EQUAL_TYPES = CurrencyConversionResponse(
        hashMapOf("USD" to 1.1876), "EUR", "2020-09-14"
    )

    val CURRENCY_CONVERSIONS: List<CurrencyConversionResponse> = Collections.unmodifiableList(
        object : ArrayList<CurrencyConversionResponse>() {
            init {
                add(
                    CurrencyConversionResponse(
                    )
                )
                add(
                    CurrencyConversionResponse(
                    )
                )
            }
        }
    )
}