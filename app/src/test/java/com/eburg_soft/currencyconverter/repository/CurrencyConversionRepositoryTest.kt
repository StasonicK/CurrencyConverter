package com.eburg_soft.currencyconverter.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.eburg_soft.currencyconverter.core.datatype.Result
import com.eburg_soft.currencyconverter.core.datatype.ResultType.SUCCESS
import com.eburg_soft.currencyconverter.data.datasource.database.CurrencyConversionDatabase
import com.eburg_soft.currencyconverter.data.datasource.database.daos.CurrencyConversionDao
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.datasource.network.CurrenciesNetworkDataSource
import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepositoryImpl
import com.eburg_soft.currencyconverter.utils.CoroutineTestRule
import com.eburg_soft.currencyconverter.utils.TestUtil
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.*
import org.junit.rules.*
import java.util.Collections

@ExtendWith(MockKExtension::class)
@ExperimentalCoroutinesApi
class CurrencyConversionRepositoryTest {
    // TODO: 19.09.2020 fix class

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

        @MockK
    private lateinit var currencyConversionDatabase: CurrencyConversionDatabase

    //@SpyK
    //@MockK
//    private lateinit var currencyConversionDao: CurrencyConversionDao
    private val currencyConversionDao = mockk<CurrencyConversionDao>(relaxUnitFun = true)

    @MockK
    private lateinit var currenciesNetworkDataSource: CurrenciesNetworkDataSource

    private lateinit var currencyConversionRepositoryImpl: CurrencyConversionRepositoryImpl

    @Before
    fun init() {
        MockKAnnotations.init(this)
        currencyConversionRepositoryImpl =
            CurrencyConversionRepositoryImpl(currencyConversionDatabase, currenciesNetworkDataSource)
//        currencyConversionDao = currencyConversionDatabase.currencyConversationDao()
    }

    @After
    fun finish() {
        unmockkAll()
    }

    /*
        get currencies from database
     */
    @Test
    @Throws(Exception::class)
    fun returnAllCurrenciesFromDB() = coroutineTestRule.runBlockingTest {
        //  Arrange
        val currencies: List<CurrencyConversionEntity> = Collections.unmodifiableList(
            object : ArrayList<CurrencyConversionEntity>() {
                init {
                    add(TestUtil.CURRENCY_CONVERSION_ONE)
                    add(TestUtil.CURRENCY_CONVERSION_TWO)
                }
            }
        )
        val expectedLiveData = MutableLiveData<List<CurrencyConversionEntity>>()
        expectedLiveData.postValue(currencies)
        coEvery { currencyConversionDao.getAllCurrencyConversions() } returns expectedLiveData

        //  Act
        val result = currencyConversionRepositoryImpl.getAllCurrencyConversions()

        //  Assert
        coVerify(exactly = 1) { currencyConversionRepositoryImpl.getAllCurrencyConversions() }
//        Assertions.assertEquals(expectedLiveData, result)
//        println(expectedLiveData)
//        println(result)
    }

    /*
        get successful result of currency conversion of the same currencies from API
     */
    @Test
    @Throws(Exception::class)
    fun exchangeCurrencyRatesEqualTypes_successfulResult() = coroutineTestRule.runBlockingTest {
        //  Arrange
        val response = TestUtil.CURRENCY_CONVERSION_RES_EQUAL_TYPES
        val expectedResult = Result(SUCCESS, response)
        coEvery { currenciesNetworkDataSource.getExchangeRates(any()) } returns expectedResult

        //  Act
        val result = currencyConversionRepositoryImpl.getExchangeRates("USD,USD")

        //  Assert
        coVerify(exactly = 1) { currenciesNetworkDataSource.getExchangeRates(any()) }
        Assertions.assertEquals(expectedResult, result)
        println(expectedResult)
        println(result)
    }

    /*
        get successful result of currency conversion of the different currencies from API
     */
    @Test
    @Throws(Exception::class)
    fun exchangeCurrencyRatesDifferentTypes_successfulResult() = coroutineTestRule.runBlockingTest {
        //  Arrange
        val response = TestUtil.CURRENCY_CONVERSION_RES_TWO
        val expectedResult = Result.success(response)
        coEvery { currenciesNetworkDataSource.getExchangeRates(any()) } returns expectedResult

        //  Act
        val result: Result<CurrencyConversionResponse> = currencyConversionRepositoryImpl.getExchangeRates("HKD,USD")

        //  Assert
        coVerify(exactly = 1) { currenciesNetworkDataSource.getExchangeRates(any()) }
        Assertions.assertEquals(expectedResult, result)
        println(expectedResult)
        println(result)
    }

    /*
        save a currency conversion in database
     */
    @Test
    @Throws(Exception::class)
    fun saveCurrencyConversion() = coroutineTestRule.runBlockingTest {
        //  Arrange
        val data = TestUtil.CURRENCY_CONVERSION_ONE
//        coEvery { currencyConversionRepositoryImpl.saveCurrencyConversion(any()) } just Runs
        coEvery { currencyConversionRepositoryImpl.saveCurrencyConversion(data) } just Runs

        //  Act
        currencyConversionRepositoryImpl.saveCurrencyConversion(data)

        //  Assert
        coVerify(exactly = 1) { currencyConversionRepositoryImpl.saveCurrencyConversion(any()) }
    }

    /*
        remove a currency conversion in database
     */
    @Test
    @Throws(Exception::class)
    fun removeCurrencyConversions() = coroutineTestRule.runBlockingTest {
        //  Arrange
        coEvery { currencyConversionDao.deleteAllCurrencyConversions() } just Runs

        //  Act
        currencyConversionRepositoryImpl.removeAllCurrenciesConversions()

        //  Assert
        coVerify(exactly = 1) { currencyConversionDao.deleteAllCurrencyConversions() }
    }
}