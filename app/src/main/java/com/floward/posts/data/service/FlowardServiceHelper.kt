package com.floward.posts.data.service

import android.content.Context
import com.floward.posts.api.apiHelper.RetrofitApiHelper
import okhttp3.OkHttpClient
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.concurrent.TimeUnit

internal class FlowardServiceHelper(
    private val context: Context, private val client: OkHttpClient,
    private val baseApiUrl: String
) : RetrofitApiHelper<FlowardService>() {
    override fun createTransactRetrofit(): FlowardService {
        return createRetrofit(baseApiUrl, client).create(FlowardService::class.java)
    }

    override fun createTransactRetrofitMock(): FlowardService {
        val retrofit = createRetrofit(baseApiUrl, client)
        val behavior = NetworkBehavior.create()
        behavior.setDelay(500, TimeUnit.MILLISECONDS)
        val mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(behavior).build()
        val delegate: BehaviorDelegate<FlowardService> =
            mockRetrofit.create(FlowardService::class.java)

        return MockFlowardService(delegate, context)
    }
}