package com.eburg_soft.currencyconverter.data.datasource.database.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class CurrencyConversionEntity
    (
    @ColumnInfo(name = COLUMN_FIRST_CURRENCY_NUMBER) val firstCurrencyNumber: Double? = 0.00,
    @ColumnInfo(name = COLUMN_FIRST_CURRENCY_TYPE) val firstCurrencyType: String? = "",
    @ColumnInfo(name = COLUMN_SECOND_CURRENCY_NUMBER) val secondCurrencyNumber: Double? = 0.00,
    @ColumnInfo(name = COLUMN_SECOND_CURRENCY_TYPE) val secondCurrencyType: String? = "",
    @ColumnInfo(name = COLUMN_CURRENCIES_RATE) val currenciesRate: Double? = 1.00,
    @ColumnInfo(name = COLUMN_DATE) val date: String = ""
) : Parcelable {

    @ColumnInfo(name = COLUMN_ID)
    @PrimaryKey(autoGenerate = true)
    var conversionId: Int = 0

    companion object {

        const val TABLE_NAME = "currency_conversions"
        const val COLUMN_ID = "currency_id"
        const val COLUMN_FIRST_CURRENCY_NUMBER = "first_currency_number"
        const val COLUMN_FIRST_CURRENCY_TYPE = "first_currency_type"
        const val COLUMN_SECOND_CURRENCY_NUMBER = "second_currency_number"
        const val COLUMN_SECOND_CURRENCY_TYPE = "second_currency_type"
        const val COLUMN_CURRENCIES_RATE = "currencies_rate"
        const val COLUMN_DATE = "date"
    }
}