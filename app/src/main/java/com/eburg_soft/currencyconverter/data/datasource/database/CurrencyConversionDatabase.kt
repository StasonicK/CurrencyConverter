package com.eburg_soft.currencyconverter.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eburg_soft.currencyconverter.data.datasource.database.daos.CurrencyConversionDao
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity

@Database(
    entities = [CurrencyConversionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CurrencyConversionDatabase : RoomDatabase() {

    abstract fun currencyConversationDao(): CurrencyConversionDao
}