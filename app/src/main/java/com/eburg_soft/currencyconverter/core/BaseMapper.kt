package com.eburg_soft.currencyconverter.core

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}