package com.floward.posts.domain.repository

import com.floward.posts.api.Result
import com.floward.posts.domain.model.UserModel

interface FlowardRepository {
    suspend fun getPosts(): Result<List<UserModel>?>
    suspend fun getUsers(): Result<List<UserModel>?>
}
