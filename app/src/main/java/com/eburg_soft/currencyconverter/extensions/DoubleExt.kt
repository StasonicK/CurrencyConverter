package com.eburg_soft.currencyconverter.extensions

import timber.log.Timber
import java.math.RoundingMode.HALF_UP
import java.text.NumberFormat
import java.util.Locale

const val SECOND_CURRENCY_RATE_ZERO = "Second currency rate is 0!"

@Throws(Exception::class)
fun Double.countCurrenciesRate(secondCurrencyRate: Double): Double {
    if (secondCurrencyRate == 0.0) throw Exception(SECOND_CURRENCY_RATE_ZERO)
    val number = secondCurrencyRate / this

    return number.round(2)
}

fun Double.round(number: Int): Double {
    val nf = NumberFormat.getNumberInstance(Locale.US)
    nf.apply {
        roundingMode = HALF_UP
        maximumFractionDigits = number
    }
    val result = nf.format(this).replace(",", "").toDouble()
    Timber.d("Rounded Double is %s", result)
    return result
}