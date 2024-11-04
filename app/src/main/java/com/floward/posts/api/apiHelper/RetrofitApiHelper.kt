package com.floward.posts.api.apiHelper

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

abstract class RetrofitApiHelper<out T> {

    protected open fun createRetrofit(baseApiUrl: String, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    //For transact
    abstract fun createTransactRetrofit(): T

    abstract fun createTransactRetrofitMock(): T
}