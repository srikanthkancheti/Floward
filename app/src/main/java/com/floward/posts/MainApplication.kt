package com.floward.posts

import android.app.Application
import com.floward.posts.di.appModule
import com.floward.posts.data.di.dataModule
import com.floward.posts.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    appModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }
}
