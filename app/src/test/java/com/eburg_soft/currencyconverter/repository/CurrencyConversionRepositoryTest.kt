package com.eburg_soft.currencyconverter.repository

import com.eburg_soft.currencyconverter.data.datasource.database.CurrencyConversionDatabase
import com.eburg_soft.currencyconverter.data.datasource.network.CurrencyConversionNetworkDataSource
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepositoryImpl
import com.eburg_soft.currencyconverter.utils.InstantExecutorExtension
import org.junit.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.*
import org.mockito.*

@ExtendWith(InstantExecutorExtension::class)
class CurrencyConversionRepositoryTest {

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



    /*

     */
    @Test
    @Throws(Exception::class)
    fun i() {
        //  Arrange
        val returnedData = 1
//        Mockito.`when`()

        //  Act


        //  Assert

        //  insert
//        val currencyConversion =
    }


}