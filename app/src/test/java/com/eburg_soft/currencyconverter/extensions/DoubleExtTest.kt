package com.eburg_soft.currencyconverter.extensions

import com.eburg_soft.currencyconverter.utils.InstantExecutorExtension
import org.junit.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.*

@ExtendWith(InstantExecutorExtension::class)
class DoubleExtTest {

    /*
        Round 0 places of decimals
     */
    @Test
    @Throws(Exception::class)
    fun roundZeroFractionalNumber() {
        val expectedNumber = 100.0
        val resultNumber = expectedNumber.round(0)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal numbers are equal: $expectedNumber == $resultNumber")
    }

    /*
        Round 2 places of decimals number to 0 places of decimals
     */
    @Test
    @Throws(Exception::class)
    fun roundTwoDecimalsNumber_zeroFractional() {
        val expectedNumber = 100.0
        val testNumber = 100.12
        val resultNumber = testNumber.round(0)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal numbers are equal: $expectedNumber == $resultNumber")
    }

    /*
        Round a decimal number with 5 at the fractional end
     */
    @Test
    @Throws(Exception::class)
    fun roundFractionalNumberWithFiveAtTheEnd() {
        val expectedNumber = 100.0
        val testNumber = 100.05
        val resultNumber = testNumber.round(1)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal numbers are equal: $expectedNumber == $resultNumber")
    }

    /*
        Round a decimal number with 49 at the fractional end
     */
    @Test
    @Throws(Exception::class)
    fun roundFractionalNumberWithFourtyNineAtTheEnd() {
        val expectedNumber = 100.0
        val testNumber = 100.49
        val resultNumber = testNumber.round(0)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal numbers are equal: $expectedNumber == $resultNumber")
    }

    /*
        Round 2 decimal number to 4 places of decimals
     */
    @Test
    @Throws(Exception::class)
    fun roundTwoDecimalNumber_fourDecimals() {
        val expectedNumber = 100.1200
        val testNumber = 100.12
        val resultNumber = testNumber.round(4)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal numbers are equal: $expectedNumber == $resultNumber")
    }

    /*
        Set number to the field,
        get resulting number
     */
    @Test
    @Throws(Exception::class)
    fun setRegularNumber_returnResult() {
        val expectedNumber = 1.00
        val firstCurrencyNumber = 10.00
        val secondCurrencyNumber = 10.00
        val resultNumber = firstCurrencyNumber.countCurrenciesRate(secondCurrencyNumber)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal numbers are equal: $expectedNumber == $resultNumber")
    }

    /*
        Set zero to the field,
        get Arithmetic exception
     */
    @Test
    @Throws(Exception::class)
    fun setZeroSecondCurrencyRate_throwArithmeticException() {
        val exception: Exception = Assertions.assertThrows(Exception::class.java) {
            val testDecimal = 1.234
            testDecimal.countCurrenciesRate(0.0)
        }

        Assertions.assertEquals(SECOND_CURRENCY_RATE_ZERO, exception.message)
        println("Thrown  exception: $SECOND_CURRENCY_RATE_ZERO")
    }
}