package com.floward.posts.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.floward.posts.domain.model.UserModel
import org.koin.core.component.KoinComponent

class ViewUserPostsViewModel(val shared: SharedFlowrdViewModel): ViewModel(), KoinComponent {

    fun getUserPostsData(userId: String): List<UserModel> {
        return shared.getUserPostsData(userId)
    }

    fun isPostsNotEmpty(userId: String): Boolean {
        return shared.getUserPostsData(userId).isNotEmpty()
    }
}