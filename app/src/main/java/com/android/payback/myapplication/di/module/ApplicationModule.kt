package com.pixabay.di.modules

import android.util.Log
import com.android.payback.myapplication.repo.RestService
import com.android.payback.myapplication.repo.RetrofitServiceGenerator
import com.android.payback.myapplication.utils.Cons
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        converter: GsonConverterFactory,
        httpClient: OkHttpClient.Builder,
        @Named("baseURL") baseURL: String
    ): Retrofit {
        val retrofitClass =
            RetrofitServiceGenerator(
                converter,
                httpClient,
                baseURL
            )
        return retrofitClass.getClient()
    }

    @Singleton
    @Provides
    fun provideOkHttp(interceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        val httpClient = OkHttpClient().newBuilder()
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.callTimeout(30, TimeUnit.SECONDS)
        httpClient.addInterceptor(interceptor)
        return httpClient
    }

    @Singleton
    @Provides
    fun provedHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
               //  Logger.d(message)
                Log.d("okhttp", message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideGSONConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    @Named("baseURL")
    fun provideBaseURL(): String {
        return Cons.BASE_URL
    }

    @Singleton
    @Provides
    fun getMainInterface(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }

}