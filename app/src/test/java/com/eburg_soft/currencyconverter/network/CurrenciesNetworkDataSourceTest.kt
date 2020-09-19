package com.eburg_soft.currencyconverter.network

import com.eburg_soft.currencyconverter.core.datatype.Result
import com.eburg_soft.currencyconverter.data.datasource.network.CurrenciesApi
import com.eburg_soft.currencyconverter.data.datasource.network.CurrenciesNetworkDataSource
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

@ExtendWith(MockKExtension::class)
@ExperimentalCoroutinesApi
class CurrenciesNetworkDataSourceTest {

    @MockK
    private lateinit var currenciesApi: CurrenciesApi

    private lateinit var currenciesNetworkDataSource: CurrenciesNetworkDataSource

    @Before
    fun init() {
        MockKAnnotations.init(this)
        currenciesNetworkDataSource = CurrenciesNetworkDataSource(currenciesApi)
    }

    @After
    fun finish() {
        unmockkAll()
    }

    @Test
    @Throws(Exception::class)
    fun returnsSuccessfulResult() = runBlockingTest {
        //  Arrange
        val response = TestUtil.CURRENCY_CONVERSION_RES_ONE
        val text = "USD,CAD"
        val expectedResult = Result.success(response)
        coEvery { currenciesNetworkDataSource.getExchangeRates(any()) } returns expectedResult
        coEvery { currenciesApi.getExchangeRates(any()) } returns response

        //  Act
        val result = currenciesNetworkDataSource.getExchangeRates(text)

        //  Assert
        coVerify(exactly = 1) { currenciesApi.getExchangeRates(any()) }
        Assertions.assertEquals(expectedResult, result)
    }
}