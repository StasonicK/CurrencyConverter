package com.eburg_soft.currencyconverter.data.datasource.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity

@Dao
interface CurrencyConversionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCurrencyConversion(currencyConversionEntity: CurrencyConversionEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrencyConversion(currencyConversionEntity: CurrencyConversionEntity)

    @Query("DELETE FROM " + CurrencyConversionEntity.TABLE_NAME)
    fun deleteCurrencyConversions()

    @Query("SELECT * FROM ${CurrencyConversionEntity.TABLE_NAME}")
    fun getAllCurrencyConversions(): LiveData<List<CurrencyConversionEntity>>
}