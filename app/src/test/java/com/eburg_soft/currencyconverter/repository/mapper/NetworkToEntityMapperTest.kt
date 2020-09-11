package com.eburg_soft.currencyconverter.repository.mapper

import com.eburg_soft.currencyconverter.data.datasource.database.CurrencyConversionDatabase
import com.eburg_soft.currencyconverter.data.datasource.network.CurrencyConversionNetworkDataSource
import com.eburg_soft.currencyconverter.data.repository.CurrencyConversionRepositoryImpl
import com.eburg_soft.currencyconverter.data.repository.mapper.NetworkToEntityMapper
import com.eburg_soft.currencyconverter.utils.TestUtil
import org.junit.jupiter.api.*
import org.mockito.*

class NetworkToEntityMapperTest {


    private lateinit var mockitoSession: MockitoSession

    //    private lateinit var currencyConversionDao: CurrencyConversionDao
    private lateinit var currencyConversionDatabase: CurrencyConversionDatabase
    private lateinit var currencyConversionNetworkDataSource: CurrencyConversionNetworkDataSource
    private lateinit var currencyConversionRepositoryImpl: CurrencyConversionRepositoryImpl

    private fun <T> any(type: Class<T>): T = Mockito.any(type)

    @BeforeEach
    fun init() {
//        mockitoSession = Mockito.mockitoSession()
//            .initMocks(this)
//            .startMocking()
//
//        currencyConversionDatabase = Mockito.mock(CurrencyConversionDatabase::class.java)
//        currencyConversionNetworkDataSource = Mockito.mock(CurrencyConversionNetworkDataSource::class.java)
//        currencyConversionRepositoryImpl =
//            CurrencyConversionRepositoryImpl(currencyConversionDatabase, currencyConversionNetworkDataSource)
    }

    @AfterEach
    fun finishEach() {
//        mockitoSession.finishMocking()
    }

    /*
        Different currency types
        -   Set first currency number,
        -   map result
    */
    @Test
    @Throws(Exception::class)
    fun differentCurrencyTypes_returnCorrectCurrencyConversionEntity() {
        //  Arrange
       val firstCurrencyNumber = 1.00
        val currencyConversionResponse = TestUtil.CURRENCY_CONVERSION_RES_ONE
        val expectedEntity = TestUtil.CURRENCY_CONVERSION_ONE
//        `when`(NetworkToEntityMapper.map(any(CurrencyConversionResponse::class.java))).thenReturn(
//            currencyConversionEntity
//        )

        // Act
        val networkToEntityMapper = NetworkToEntityMapper()
        networkToEntityMapper.setFirstCurrencyNumber(firstCurrencyNumber)
        val resultEntity = networkToEntityMapper.map(currencyConversionResponse)

        // Assert
        println("expected entity: $expectedEntity")
        println("result entity: $resultEntity")
        Assertions.assertEquals(expectedEntity, resultEntity)
        println("The currency conversions are equal!")
    }

    /*
        Same currency types
        -   Set first currency number,
        -   map result
    */
    @Test
    @Throws(Exception::class)
    fun equalCurrencyTypes_returnCorrectCurrencyConversionEntity() {
        //  Arrange
      val  firstCurrencyNumber = 1.00
        val currencyConversionResponse = TestUtil.CURRENCY_CONVERSION_RES_EQUAL_TYPES
        val expectedEntity = TestUtil.CURRENCY_CONVERSION_EQUAL_TYPES
//        `when`(NetworkToEntityMapper.map(any(CurrencyConversionResponse::class.java))).thenReturn(
//            currencyConversionEntity
//        )

        // Act
        val networkToEntityMapper = NetworkToEntityMapper()
        networkToEntityMapper.setFirstCurrencyNumber(firstCurrencyNumber)
        val resultEntity = networkToEntityMapper.map(currencyConversionResponse)

        // Assert
        println("expected entity: $expectedEntity")
        println("result entity: $resultEntity")
        Assertions.assertEquals(expectedEntity, resultEntity)
        println("The currency conversions are equal!")
    }


    /*
    Insert note, null title
    -   confirm throw exception
 */
    @Test
    @Throws(Exception::class)
    fun insertNote_nullTitle_throwException() {
    }
}