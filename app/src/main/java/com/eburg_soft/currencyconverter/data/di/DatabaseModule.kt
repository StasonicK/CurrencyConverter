package com.eburg_soft.currencyconverter.data.di

import android.content.Context
import androidx.room.Room
import com.eburg_soft.currencyconverter.data.datasource.database.CurrencyConversionDatabase
import com.eburg_soft.currencyconverter.data.datasource.database.daos.CurrencyConversionDao
import toothpick.config.Module

private const val DATABASE_NAME = "currency_conversions.db"

class DatabaseModule(applicationContext: Context) : Module() {
    init {
        val db = createDatabase(applicationContext)
        val dao = db.currencyConversationDao()
        bind(CurrencyConversionDao::class.java).toInstance(dao)
    }

    private fun createDatabase(applicationContext: Context): CurrencyConversionDatabase {
        return Room.databaseBuilder(
            applicationContext,
            CurrencyConversionDatabase::class.java,
            DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
    }
}