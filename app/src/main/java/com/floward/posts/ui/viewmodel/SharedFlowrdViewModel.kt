package com.floward.posts.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.floward.posts.domain.model.UserModel
import org.koin.core.component.KoinComponent

class SharedFlowrdViewModel: ViewModel(), KoinComponent {

    var flowardUserResult: List<UserModel>? = null
    var flowardPostsResult: List<UserModel>? = null
    private var combinePostsData: List<UserModel>? = null

    fun getUsersData(): List<UserModel> {
        combinePostsData = flowardUserResult?.map { user ->
            user.postsCount = flowardPostsResult?.count { it.userId == user.userId } ?: 0
            user
        } ?: emptyList()
        return combinePostsData ?: emptyList()
    }

    fun getUserPostsData(userId: String): List<UserModel> {
        return flowardPostsResult?.filter { it.userId == userId } ?: emptyList()
    }

    fun getUserUrl(userId: String): String {
        return flowardUserResult?.find { it.userId == userId }?.photoUrl ?: ""
    }
}
