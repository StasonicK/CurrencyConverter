package com.eburg_soft.currencyconverter.features.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eburg_soft.currencyconverter.core.datatype.Result
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepositoryImpl
import com.eburg_soft.currencyconverter.data.repository.mapper.NetworkToEntityMapper
import com.eburg_soft.currencyconverter.features.converter.viewmodel.ConverterViewModel
import com.eburg_soft.currencyconverter.utils.CoroutineTestRule
import com.eburg_soft.currencyconverter.utils.TestUtil
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

@ExperimentalCoroutinesApi
class ConverterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: ConverterViewModel

    @MockK
    private lateinit var repository: CurrencyConversionRepositoryImpl

    @MockK
    private lateinit var mapper: NetworkToEntityMapper

    @Before
    fun init() {
        MockKAnnotations.init(this)
        viewModel = ConverterViewModel(repository)
    }

    @After
    fun finish() {
        unmockkAll()
    }

    /*
        save conversion of two same types
     */
    @Test
    @Throws(Exception::class)
    fun saveCurrencyConversionSameTypes() = coroutineTestRule.runBlockingTest {
        //    Arrange
        val firstCurrencyNumber = "1"
        val firstCurrenciesType = "USD"
        val secondCurrenciesType = "USD"
        coEvery {
            repository.saveCurrencyConversion(any())
        } just Runs

        //  Act
        viewModel.saveCurrencyConversion(firstCurrencyNumber, firstCurrenciesType, secondCurrenciesType)

        //  Assert
        coVerify(exactly = 1) { repository.saveCurrencyConversion(any()) }
    }

    /*
        save conversion of two different types
     */
    @ExperimentalCoroutinesApi
    @Test
    @Throws(Exception::class)
    fun saveCurrencyConversionDifferentTypes() = coroutineTestRule.runBlockingTest {
        //    Arrange
        val firstCurrencyNumber = "1"
        val firstCurrenciesType = "USD"
        val secondCurrenciesType = "CAD"
        val currencies = "$firstCurrenciesType,$secondCurrenciesType"
        val response = TestUtil.CURRENCY_CONVERSION_RES_ONE
        val entity = TestUtil.CURRENCY_CONVERSION_ONE
        val result = Result.success(response)
        coEvery {
            mapper.setFirstCurrencyNumber(any())
        } just Runs
        coEvery {
            repository.getExchangeRates(any())
        } returns result
        coEvery {
            mapper.map(any())
        } returns entity
        coEvery {
            repository.saveCurrencyConversion(any())
        } just Runs

        //  Act
        viewModel.saveCurrencyConversion(firstCurrencyNumber, firstCurrenciesType, secondCurrenciesType)

        //  Assert
//        coVerify(exactly = 1) { mapper.setFirstCurrencyNumber(any()) }
        coVerify(exactly = 1) { repository.getExchangeRates(any()) }
//        coVerify(exactly = 1) { mapper.map(any()) }
        coVerify(exactly = 1) { repository.saveCurrencyConversion(any()) }
    }
}