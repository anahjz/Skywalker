package com.skywalker.app.data.remoteDatasource

import com.skywalker.app.common.ApiRouts
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitServiceImpl : RetrofitServiceInterface {
    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(this).build()
    }

    override fun starWarsApiService(): StarWarsApi {
        val service = Retrofit.Builder()
            .baseUrl(ApiRouts.SWAPI)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return service.create(StarWarsApi::class.java)
    }

}