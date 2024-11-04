package com.floward.posts.domain.usecase

import com.floward.posts.api.Result
import com.floward.posts.api.network.SimpleError
import com.floward.posts.api.usecase.CoroutineUseCase
import com.floward.posts.domain.model.UserModel
import com.floward.posts.domain.repository.FlowardRepository
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.component.KoinComponent

class GetPostsUseCase(
    private val flowardRepository: FlowardRepository,
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, List<UserModel>?>(coroutineDispatcher), KoinComponent {

    override suspend fun execute(): Result<List<UserModel>?> {
//        val postsFlow = flow { emit(flowardRepository.getPosts()) }
//        val usersFlow = flow { emit(flowardRepository.getUsers()) }
//        val combineResponse = postsFlow.zip(usersFlow) { post, user ->
//            CombineData(
//                userModel = user.succeeded
//            )
//        }
        return when (val result = flowardRepository.getPosts()) {
            is Result.Loading -> Result.Loading
            is Result.Success -> {
                Result.Success(result.data)
            }
            is Result.Error -> Result.Error(SimpleError())

        }
    }
}
