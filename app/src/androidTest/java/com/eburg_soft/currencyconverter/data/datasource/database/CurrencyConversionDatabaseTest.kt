package com.eburg_soft.currencyconverter.data.datasource.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.eburg_soft.currencyconverter.data.datasource.database.daos.CurrencyConversionDao
import org.junit.*

//@SmallTest
//@RunWith(AndroidJUnit4::class)
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