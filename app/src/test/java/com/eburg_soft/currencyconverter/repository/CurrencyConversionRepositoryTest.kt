package com.eburg_soft.currencyconverter.repository

import com.eburg_soft.currencyconverter.core.datatype.Result
import com.eburg_soft.currencyconverter.data.datasource.database.CurrencyConversionDatabase
import com.eburg_soft.currencyconverter.data.datasource.database.daos.CurrencyConversionDao
import com.eburg_soft.currencyconverter.data.datasource.network.CurrencyConversionNetworkDataSource
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepositoryImpl
import com.eburg_soft.currencyconverter.utils.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*
import org.robolectric.RobolectricTestRunner

//@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
//@RunWith(RobolectricTestRunner::class)
class CurrencyConversionRepositoryTest {

    private lateinit var mockitoSession: MockitoSession

    private lateinit var currencyConversionDao: CurrencyConversionDao
    private lateinit var currencyConversionDatabase: CurrencyConversionDatabase
    private lateinit var currencyConversionNetworkDataSource: CurrencyConversionNetworkDataSource
    private lateinit var currencyConversionRepositoryImpl: CurrencyConversionRepositoryImpl

    private fun <T> any(type: Class<T>): T = Mockito.any(type)

    @Before
    fun initEach() {
        mockitoSession = mockitoSession()
            .initMocks(this)
            .startMocking()

        currencyConversionDatabase = mock(CurrencyConversionDatabase::class.java)
//        currencyConversionDao = currencyConversionDatabase.currencyConversationDao()
        currencyConversionNetworkDataSource = mock(CurrencyConversionNetworkDataSource::class.java)
        currencyConversionRepositoryImpl =
            CurrencyConversionRepositoryImpl(currencyConversionDatabase, currencyConversionNetworkDataSource)
    }

    @After
    fun finishEach() {
        mockitoSession.finishMocking()
    }

    /*

     */
    @Test
    @Throws(Exception::class)
    fun exchangeCurrencyRatesEqualTypes_correctResult(): Unit = runBlocking {
        //  Arrange
        val currencyConversionResponse = TestUtil.CURRENCY_CONVERSION_RES_EQUAL_TYPES
        val currencies = "USD,USD"
        val expectedResult = Result.success(currencyConversionResponse)
        `when`(currencyConversionRepositoryImpl.getExchangeRates(currencies)).thenReturn(expectedResult)

        //  Act
        val resultConversionResponse = currencyConversionRepositoryImpl.getExchangeRates(currencies)
//        currencyConversionRepositoryImpl.getAllCurrencyConversions()

        //  Assert
//        Assertions.assertEquals(expectedResult, resultConversionResponse)
        println("$expectedResult")
        println("$resultConversionResponse")
    }


    /*

     */
    @Test
    @Throws(Exception::class)
    fun insertRemoveCurrencies_correctResult(): Unit = runBlocking {

    }
}