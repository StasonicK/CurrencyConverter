package com.eburg_soft.currencyconverter.data.datasource.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object CurrenciesApiService {

    fun currencyApi(baseUrl: String): CurrenciesApi = retrofitInstance(baseUrl).create(CurrenciesApi::class.java)

    private fun okHttpInstance(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient().newBuilder()
            .addInterceptor(logging)
            .build()
    }

    private fun gsonInstance(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    private fun retrofitInstance(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpInstance())
            .addConverterFactory(GsonConverterFactory.create(gsonInstance()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}