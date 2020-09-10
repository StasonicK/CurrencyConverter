package com.eburg_soft.currencyconverter.data.datasource.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.*

abstract class CurrencyConversionEntityRepositoryDaoTest : CurrencyConversionEntityRepositoryDatabaseTest() {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    /*
        Insert a currency conversion,
        read a currency conversion,
        delete a currency conversion
     */
    @Test
    @Throws(Exception::class)
    fun insertReadDeleteOneCurrencyConversion() {
        //  insert
//        val currencyConversion =
    }

    /*
        Insert currency conversions,
        read ids,
        delete a currency conversions
     */

    /*
        Insert a currency conversion,
        read a currency conversion,
        delete a currency conversion
     */

    /*
        Insert a currency conversion,
        read a currency conversion,
        delete a currency conversion
     */
}