package com.floward.posts.di

import com.floward.posts.BuildConfig
import com.floward.posts.ui.viewmodel.SharedFlowrdViewModel
import com.floward.posts.domain.config.FlowardConfig
import com.floward.posts.ui.viewmodel.ViewUsersViewModel
import com.floward.posts.ui.viewmodel.ViewUserPostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SharedFlowrdViewModel() }
    viewModel { (sharedViewModel: SharedFlowrdViewModel) -> ViewUsersViewModel(shared = sharedViewModel, get(), get()) }
    viewModel { (sharedViewModel: SharedFlowrdViewModel) -> ViewUserPostsViewModel(shared = sharedViewModel) }
    single { getFlowardConfig() }
}

private fun getFlowardConfig(): FlowardConfig {
    val flowardConfig = FlowardConfig()
    flowardConfig.isMock = false
    flowardConfig.isProduction = false
    flowardConfig.baseUrl = BuildConfig.BASE_URL
    flowardConfig.baseUrlWithSubDomain = BuildConfig.BASE_URL_WITH_DOMAIN
    return flowardConfig
}
