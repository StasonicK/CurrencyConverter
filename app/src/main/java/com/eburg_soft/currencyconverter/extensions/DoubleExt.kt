package com.eburg_soft.currencyconverter.extensions

import timber.log.Timber
import java.math.RoundingMode.HALF_DOWN
import java.math.RoundingMode.HALF_EVEN
import java.math.RoundingMode.HALF_UP
import java.text.NumberFormat
import java.util.Locale

fun Double.countFirstCurrencyToSecondCurrencyRate(secondCurrencyRate: Double): Double {
    val number = this / secondCurrencyRate
    return number.round(4)
}

fun Double.round(number: Int): Double {
    val nf = NumberFormat.getNumberInstance(Locale.US)
    nf.apply {
        roundingMode = HALF_UP
        maximumFractionDigits = number
    }
    var result = 0.0
    try {
        result = nf.format(this).toDouble()
    } catch (e: Exception) {
        e.printStackTrace()
        Timber.e(e, "Exception %s", e.message)
    }
    Timber.d("Rounded Double is %s", result)
    return result
}