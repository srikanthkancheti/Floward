package com.floward.posts.domain.di

import com.floward.posts.domain.usecase.GetPostsUseCase
import com.floward.posts.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val IO_DISPATCHER = "IO"
private const val MAIN_DISPATCHER = "Main"
private const val DEFAULT_DISPATCHER = "Default"

val domainModule = module {
    factory { GetPostsUseCase(get(), get(named(IO_DISPATCHER))) }
    factory { GetUsersUseCase(get(), get(named(IO_DISPATCHER))) }
    loadCoroutineDispatchers()
}

private fun Module.loadCoroutineDispatchers() {
    factory<CoroutineDispatcher>(named(IO_DISPATCHER)) { Dispatchers.IO }
    factory<CoroutineDispatcher>(named(MAIN_DISPATCHER)) { Dispatchers.Main }
    factory<CoroutineDispatcher>(named(DEFAULT_DISPATCHER)) { Dispatchers.Default }
}