package com.floward.posts.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.floward.posts.api.Event
import com.floward.posts.api.Result
import com.floward.posts.domain.model.UserModel
import com.floward.posts.domain.usecase.GetPostsUseCase
import com.floward.posts.domain.usecase.GetUsersUseCase
import org.koin.core.component.KoinComponent

class ViewUsersViewModel(
    val shared: SharedFlowrdViewModel,
    private val getPostsUseCase: GetPostsUseCase,
    private val getUsersUseCase: GetUsersUseCase
): ViewModel(), KoinComponent {

    internal var getUsersTrigger = MutableLiveData<Unit>()
    internal var getPostsTrigger = MutableLiveData<Unit>()

    // Transformations
    val getUsersResult = getUsersTrigger.switchMap {
        liveData {
            emit(Event(Result.Loading))
            val getUsersResult = getUsersUseCase(it)
            if (getUsersResult is Result.Success) {
                shared.flowardUserResult = getUsersResult.data
            }
            emit(Event(getUsersResult))
        }
    }

    // Transformations
    val getPostsResult = getPostsTrigger.switchMap {
        liveData {
            emit(Event(Result.Loading))
            val getPostsResult = getPostsUseCase(it)
            if (getPostsResult is Result.Success) {
                shared.flowardPostsResult = getPostsResult.data
            }
            emit(Event(getPostsResult))
        }
    }

    fun isUsersNotEmpty(): Boolean {
        return shared.getUsersData().isNotEmpty()
    }

    fun isUsersEmpty(): Boolean {
        return shared.getUsersData().isEmpty()
    }

    fun getUsersList(): List<UserModel> {
        return shared.getUsersData()
    }
}
