package com.eburg_soft.currencyconverter.extensions

import org.junit.Test
import org.junit.jupiter.api.*

class DoubleExtTest {

    /*
        Round 0 places of decimals
     */
    @Test
    @Throws(Exception::class)
    fun roundZeroFractionalDigit() {
        val expectedNumber = 100.0
        val resultNumber = expectedNumber.round(0)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal digits are equal: $expectedNumber == $resultNumber")
    }

    /*
        Round 2 places of decimals digit to 0 places of decimals
     */
    @Test
    @Throws(Exception::class)
    fun roundTwoDecimalsDigit_zeroFractional() {
        val expectedNumber = 100.0
        val testNumber = 100.12
        val resultNumber = testNumber.round(0)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal digits are equal: $expectedNumber == $resultNumber")
    }

    /*
        Round a decimal digit with 5 at the fractional end
     */
    @Test
    @Throws(Exception::class)
    fun roundFractionalDigitWithFiveAtTheEnd() {
        val expectedNumber = 100.0
        val testNumber = 100.05
        val resultNumber = testNumber.round(1)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal digits are equal: $expectedNumber == $resultNumber")
    }

    /*
        Round a decimal digit with 49 at the fractional end
     */
    @Test
    @Throws(Exception::class)
    fun roundFractionalDigitWithFourtyNineAtTheEnd() {
        val expectedNumber = 100.0
        val testNumber = 100.49
        val resultNumber = testNumber.round(0)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal digits are equal: $expectedNumber == $resultNumber")
    }

    /*
        Round 2 decimal digit to 4 places of decimals
     */
    @Test
    @Throws(Exception::class)
    fun roundTwoDecimal_fourDecimals() {
        val expectedNumber = 100.1200
        val testNumber = 100.12
        val resultNumber = testNumber.round(4)

        Assertions.assertEquals(expectedNumber, resultNumber)
        println("The decimal digits are equal: $expectedNumber == $resultNumber")
    }
}