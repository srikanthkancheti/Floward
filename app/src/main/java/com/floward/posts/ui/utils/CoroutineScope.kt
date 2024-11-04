package com.floward.posts.ui.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

fun getScopeForSaving() : CoroutineScope {
    // Initialization of job
    val job = Job()
    // Initialization of scope for the coroutine to run in
    return CoroutineScope(job + Dispatchers.Main)
}