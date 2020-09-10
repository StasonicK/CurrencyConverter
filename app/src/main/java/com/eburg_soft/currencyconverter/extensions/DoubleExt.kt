package com.eburg_soft.currencyconverter.extensions

fun Double.countFirstCurrencyToSecondCurrencyRate(secondCurrencyRate: Double): Double {
    val number = this / secondCurrencyRate
    return String.format("%.4f", number).toDouble()
}

fun Double.round(number: Int): Double {
    return String.format("%.$number", this).toDouble()
}