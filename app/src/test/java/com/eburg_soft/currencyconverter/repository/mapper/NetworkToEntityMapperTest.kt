package com.eburg_soft.currencyconverter.repository.mapper

import com.eburg_soft.currencyconverter.data.repository.mapper.NetworkToEntityMapper
import com.eburg_soft.currencyconverter.utils.TestUtil
import org.junit.jupiter.api.*

class NetworkToEntityMapperTest {

    private val mapper: NetworkToEntityMapper = NetworkToEntityMapper()

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

        // Act
        mapper.setFirstCurrencyNumber(firstCurrencyNumber)
        val resultEntity = mapper.map(currencyConversionResponse)

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
        val firstCurrencyNumber = 1.00
        val currencyConversionResponse = TestUtil.CURRENCY_CONVERSION_RES_EQUAL_TYPES
        val expectedEntity = TestUtil.CURRENCY_CONVERSION_EQUAL_TYPES

        // Act
        mapper.setFirstCurrencyNumber(firstCurrencyNumber)
        val resultEntity = mapper.map(currencyConversionResponse)

        // Assert
        println("expected entity: $expectedEntity")
        println("result entity: $resultEntity")
        Assertions.assertEquals(expectedEntity, resultEntity)
        println("The currency conversions are equal!")
    }
}