package com.android.payback.myapplication.repo

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitServiceGenerator @Inject constructor(
    private val converter: GsonConverterFactory,
    private val httpClient: OkHttpClient.Builder,
    private val baseURL: String
) {


    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(converter)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }
}