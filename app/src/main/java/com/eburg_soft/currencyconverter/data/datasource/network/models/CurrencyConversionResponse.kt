package com.eburg_soft.currencyconverter.data.datasource.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyConversionResponse(
    @SerializedName("rates")
    var rates: HashMap<String, Double>? = null,

    @SerializedName("base")
    var base: String? = null,

    @SerializedName("date")
    var date: String? = null
) : Parcelable