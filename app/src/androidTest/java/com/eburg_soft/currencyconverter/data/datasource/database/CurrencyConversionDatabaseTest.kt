package com.eburg_soft.currencyconverter.data.datasource.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.eburg_soft.currencyconverter.data.datasource.database.daos.CurrencyConversionDao
import org.junit.*
import org.junit.runner.*

@SmallTest
@RunWith(AndroidJUnit4::class)
abstract class CurrencyConversionDatabaseTest {

    private lateinit var currencyConversionDatabase: CurrencyConversionDatabase

    fun getCurrencyConversionDao(): CurrencyConversionDao = currencyConversionDatabase.currencyConversationDao()

    @Before
    fun init() {
        currencyConversionDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CurrencyConversionDatabase::class.java
        ).build()
    }

    @After
    fun finish() {
        currencyConversionDatabase.close()
    }
}