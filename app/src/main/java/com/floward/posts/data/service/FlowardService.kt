package com.floward.posts.data.service

import com.floward.posts.data.model.PostsResponse
import com.floward.posts.data.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET


internal interface FlowardService {
    @GET("SharminSirajudeen/test_resources/posts")
    suspend fun getPosts(): Response<List<PostsResponse>>

    @GET("SharminSirajudeen/test_resources/users")
    suspend fun getUsers(): Response<List<UsersResponse>>
}