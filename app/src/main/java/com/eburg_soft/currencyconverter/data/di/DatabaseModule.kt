package com.eburg_soft.currencyconverter.data.di

import android.content.Context
import androidx.room.Room
import com.eburg_soft.currencyconverter.data.datasource.database.CurrencyConversionDatabase
import toothpick.config.Module

private const val DATABASE_NAME = "currency_conversions.db"

class DatabaseModule(applicationContext: Context) : Module() {
    init {
        bind(CurrencyConversionDatabase::class.java).toInstance(createDatabase(applicationContext))
    }

    private fun createDatabase(applicationContext: Context): CurrencyConversionDatabase {
        return Room.databaseBuilder(
            applicationContext,
            CurrencyConversionDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}