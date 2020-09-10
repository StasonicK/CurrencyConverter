package com.eburg_soft.currencyconverter.data.di

import com.eburg_soft.currencyconverter.data.datasource.network.CurrenciesApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.config.Module

private const val BASE_URL = "https://api.exchangeratesapi.io/"

class NetworkModule : Module() {
    init {
        bind(CurrenciesApi::class.java)
            .to(currencyApi()::class.java)
            .singleton()
    }

    private fun currencyApi(): CurrenciesApi = retrofitInstance().create(CurrenciesApi::class.java)

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

    private fun retrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpInstance())
            .addConverterFactory(GsonConverterFactory.create(gsonInstance()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}