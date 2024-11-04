package com.floward.posts.data.repository

import com.floward.posts.api.Result
import com.floward.posts.api.network.SimpleError
import com.floward.posts.api.network.safeApiCall
import com.floward.posts.data.mapper.toPostsResponseUseCaseModel
import com.floward.posts.data.mapper.toUserResponseUseCaseModel
import com.floward.posts.data.service.FlowardService
import com.floward.posts.domain.model.UserModel
import com.floward.posts.domain.repository.FlowardRepository

@Suppress("detekt.TooGenericExceptionCaught")
internal class FlowardServiceImpl(private val flowardService: FlowardService) : FlowardRepository {

    override suspend fun getPosts(): Result<List<UserModel>?> {
        return safeApiCall({
            try {
                val photosResponse = flowardService.getPosts().body()
                Result.Success(photosResponse?.toPostsResponseUseCaseModel())
            } catch (e: Exception) {
                Result.Error(SimpleError())
            }
        })
    }

    override suspend fun getUsers(): Result<List<UserModel>?> {
        return safeApiCall({
            try {
                val photosResponse = flowardService.getUsers().body()
                Result.Success(photosResponse?.toUserResponseUseCaseModel())
            } catch (e: Exception) {
                Result.Error(SimpleError())
            }
        })
    }
}
