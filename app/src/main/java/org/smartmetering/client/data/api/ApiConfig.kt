package org.smartmetering.client.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.smartmetering.client.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {
    private var retrofit: Retrofit? = null
    private val duration = 120L
    private val seconds = TimeUnit.SECONDS

    private val okHttpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(duration, seconds)
        .writeTimeout(duration, seconds)
        .readTimeout(duration, seconds)
        .build()

    fun <T> createService(services: Class<T>?): T {
        if (retrofit == null) retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClientBuilder)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!.create(services!!)
    }

    val service: ApiEndpoint by lazy { createService(ApiEndpoint::class.java) }
}