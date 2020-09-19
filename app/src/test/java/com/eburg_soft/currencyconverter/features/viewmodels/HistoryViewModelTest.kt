package com.eburg_soft.currencyconverter.features.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepositoryImpl
import com.eburg_soft.currencyconverter.features.history.viewmodel.HistoryViewModel
import com.eburg_soft.currencyconverter.utils.CoroutineTestRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

@ExperimentalCoroutinesApi
class HistoryViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: HistoryViewModel

    @MockK
    private lateinit var repository: CurrencyConversionRepositoryImpl

    @Before
    fun init() {
        MockKAnnotations.init(this)
    }

    @After
    fun finish() {
        unmockkAll()
    }

    /*
        load currency conversions from database
     */
    @Test
    @Throws(Exception::class)
    fun loadCurrencyConversionsFromDb() = runBlockingTest {
        //    Arrange
        every {
            repository.getAllCurrencyConversions()
        } returns MutableLiveData()

        //  Act
        viewModel = HistoryViewModel(repository)

        //  Assert
        verify(exactly = 1) { repository.getAllCurrencyConversions() }
    }

    /*
        remove currency conversions from database
     */
    @Test
    @Throws(Exception::class)
    fun removeCurrencyConversionsFromDb() = coroutineTestRule.runBlockingTest {
        //    Arrange
        every {
            repository.getAllCurrencyConversions()
        } returns MutableLiveData()
        coEvery {
            repository.removeAllCurrenciesConversions()
        } just Runs

        //  Act
        viewModel = HistoryViewModel(repository)
        viewModel.removeAllHistory()

        //  Assert
        coVerify(exactly = 1) { repository.getAllCurrencyConversions() }
        coVerify(exactly = 1) {
            repository.removeAllCurrenciesConversions()
        }
    }
}