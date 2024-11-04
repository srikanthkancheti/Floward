package com.floward.posts.data.di

import android.app.Application
import android.content.Context
import com.floward.posts.data.repository.FlowardServiceImpl
import com.floward.posts.data.service.FlowardService
import com.floward.posts.data.service.FlowardServiceHelper
import com.floward.posts.domain.config.FlowardConfig
import com.floward.posts.domain.repository.FlowardRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    factory { getFlowardService(androidContext(), get(), get()) }
    single<FlowardRepository> { FlowardServiceImpl(get()) }
}

internal fun provideCache(application: Application): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize.toLong())
}

internal fun provideHttpClient(cache: Cache): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .cache(cache)

    return okHttpClientBuilder.build()
}

internal fun getFlowardService(
    context: Context,
    client: OkHttpClient,
    flowardConfig: FlowardConfig
): FlowardService {
    return if (flowardConfig.isMock) {
        FlowardServiceHelper(
            context,
            client,
            flowardConfig.baseUrl
        ).createTransactRetrofitMock()
    } else {
        FlowardServiceHelper(
            context,
            client,
            flowardConfig.baseUrl
        ).createTransactRetrofit()
    }
}