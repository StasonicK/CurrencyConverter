package com.eburg_soft.currencyconverter.data.datasource.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eburg_soft.currencyconverter.data.datasource.network.CurrencyConversionNetworkDataSource
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepositoryImpl
import org.junit.*
import org.junit.Test
import org.junit.jupiter.api.*
import org.mockito.*

abstract class CurrencyConversionEntityRepositoryDaoTest : CurrencyConversionEntityRepositoryDatabaseTest() {

    private lateinit var mockitoSession: MockitoSession

    //    private lateinit var currencyConversionDao: CurrencyConversionDao
    private lateinit var currencyConversionDatabase: CurrencyConversionDatabase
    private lateinit var currencyConversionNetworkDataSource: CurrencyConversionNetworkDataSource
    private lateinit var currencyConversionRepositoryImpl: CurrencyConversionRepositoryImpl

    private fun <T> any(type: Class<T>): T = Mockito.any(type)

    @BeforeEach
    fun initEach() {
        mockitoSession = Mockito.mockitoSession()
            .initMocks(this)
            .startMocking()

        currencyConversionDatabase = Mockito.mock(CurrencyConversionDatabase::class.java)
        currencyConversionNetworkDataSource = Mockito.mock(CurrencyConversionNetworkDataSource::class.java)
        currencyConversionRepositoryImpl =
            CurrencyConversionRepositoryImpl(currencyConversionDatabase, currencyConversionNetworkDataSource)
    }

    @AfterEach
    fun finishEach() {
        mockitoSession.finishMocking()
    }

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