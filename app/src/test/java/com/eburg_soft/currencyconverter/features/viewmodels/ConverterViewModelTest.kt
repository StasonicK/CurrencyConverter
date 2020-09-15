package com.eburg_soft.currencyconverter.features.viewmodels

import com.eburg_soft.currencyconverter.data.datasource.database.CurrencyConversionDatabase
import com.eburg_soft.currencyconverter.data.datasource.database.daos.CurrencyConversionDao
import com.eburg_soft.currencyconverter.data.datasource.network.CurrencyConversionNetworkDataSource
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepositoryImpl
import org.junit.jupiter.api.*
import org.mockito.*

class ConverterViewModelTest {

    private lateinit var mockitoSession: MockitoSession

    private lateinit var currencyConversionDao: CurrencyConversionDao
    private lateinit var currencyConversionDatabase: CurrencyConversionDatabase
    private lateinit var currencyConversionNetworkDataSource: CurrencyConversionNetworkDataSource
    private lateinit var currencyConversionRepositoryImpl: CurrencyConversionRepositoryImpl

    private fun <T> any(type: Class<T>): T = Mockito.any(type)

    @BeforeEach
//    @Before
    fun initEach() {
        mockitoSession = Mockito.mockitoSession()
            .initMocks(this)
            .startMocking()

//        currencyConversionDatabase = mock()
        currencyConversionDatabase = Mockito.mock(CurrencyConversionDatabase::class.java)
        currencyConversionDao = currencyConversionDatabase.currencyConversationDao()
//        currencyConversionNetworkDataSource = mock()
        currencyConversionNetworkDataSource = Mockito.mock(CurrencyConversionNetworkDataSource::class.java)
        currencyConversionRepositoryImpl =
            CurrencyConversionRepositoryImpl(currencyConversionDatabase, currencyConversionNetworkDataSource)
    }

    @AfterEach
//    @After
    fun finishEach() {
        mockitoSession.finishMocking()
    }


}