package com.floward.posts.data.service

import android.content.Context
import com.floward.posts.api.extensions.getMockResponse
import com.floward.posts.data.model.PostsResponse
import com.floward.posts.data.model.UsersResponse
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate

internal class MockFlowardService(
    private val delegate: BehaviorDelegate<FlowardService>,
    private val appContext: Context
) : FlowardService {

    override suspend fun getPosts(): Response<List<PostsResponse>> {
        val mockResponse = appContext.getMockResponse(
            "mocks/samplePostsMock.json",
            PostsResponse::class.java)
        return delegate.returningResponse(mockResponse).getPosts()
    }

    override suspend fun getUsers(): Response<List<UsersResponse>> {
        val mockResponse = appContext.getMockResponse(
            "mocks/sampleUsersMock.json",
            PostsResponse::class.java)
        return delegate.returningResponse(mockResponse).getUsers()
    }

}