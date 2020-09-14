package com.eburg_soft.currencyconverter.data.datasource.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.utils.LiveDataTestUtil
import com.eburg_soft.currencyconverter.utils.TestUtil
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.*
import kotlin.system.measureTimeMillis

class CurrencyConversionDaoTest : CurrencyConversionDatabaseTest() {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    /*
        Insert a currency conversion,
        read a currency conversion,
        delete a currency conversion
     */
    @Test
    @Throws(Exception::class)
    fun insertReadDeleteOneCurrencyConversion() = runBlocking {
        val expectedCurrencyConversion = TestUtil.CURRENCY_CONVERSION_ONE

        //  insert
        val costTimeMillis = measureTimeMillis {
            getCurrencyConversionDao().insertCurrencyConversion(expectedCurrencyConversion)
        }

        //  read
        val liveDataTestUtil = LiveDataTestUtil<List<CurrencyConversionEntity>>()
        var insertedCurrencyConversions: List<CurrencyConversionEntity>? =
            liveDataTestUtil.getValue(getCurrencyConversionDao().getAllCurrencyConversions())
        expectedCurrencyConversion.conversionId = 1
        Assert.assertNotNull(insertedCurrencyConversions)
        Assert.assertEquals(expectedCurrencyConversion, insertedCurrencyConversions?.get(0))

        //  delete
        getCurrencyConversionDao().deleteAllCurrencyConversions()

        //  read
        insertedCurrencyConversions =
            liveDataTestUtil.getValue(getCurrencyConversionDao().getAllCurrencyConversions())
        Assert.assertEquals(0, insertedCurrencyConversions?.size)
        println("passed time: $costTimeMillis")
    }

    /*
        Insert 2 currency conversions,
        read a currency conversions,
        delete a currency conversion,
        read a currency conversion,
        delete the last currency conversion
     */
    @Test
    @Throws(Exception::class)
    fun insertReadDeleteReadDeleteCurrencyConversions() = runBlocking {
        val expectedCurrencyConversion1 = TestUtil.CURRENCY_CONVERSION_ONE
        val expectedCurrencyConversion2 = TestUtil.CURRENCY_CONVERSION_TWO

        //  insert
        val costTimeMillis = measureTimeMillis {
            getCurrencyConversionDao().insertCurrencyConversion(expectedCurrencyConversion1)
            getCurrencyConversionDao().insertCurrencyConversion(expectedCurrencyConversion2)
        }

        //  read
        val liveDataTestUtil = LiveDataTestUtil<List<CurrencyConversionEntity>>()
        var insertedCurrencyConversions: List<CurrencyConversionEntity>? =
            liveDataTestUtil.getValue(getCurrencyConversionDao().getAllCurrencyConversions())
        expectedCurrencyConversion1.conversionId = 1
        expectedCurrencyConversion2.conversionId = 2
        Assert.assertNotNull(insertedCurrencyConversions)
        Assert.assertEquals(expectedCurrencyConversion1, insertedCurrencyConversions?.get(0))
        Assert.assertEquals(expectedCurrencyConversion2, insertedCurrencyConversions?.get(1))

        //  delete
        insertedCurrencyConversions?.get(0)?.let { getCurrencyConversionDao().deleteCurrencyConversion(it) }

        //  read
        insertedCurrencyConversions =
            liveDataTestUtil.getValue(getCurrencyConversionDao().getAllCurrencyConversions())
        Assert.assertNotNull(insertedCurrencyConversions)
        Assert.assertEquals(expectedCurrencyConversion2, insertedCurrencyConversions?.get(0))

        //  delete
        insertedCurrencyConversions?.get(0)?.let { getCurrencyConversionDao().deleteCurrencyConversion(it) }
        println("passed time: $costTimeMillis")
    }
}