package com.floward.posts.api.network

import com.floward.posts.api.Result


/**
 * Wrap a suspending API [call] in try/catch. In case an exception is thrown, a [Response.Error] is
 * created based on the [errorMessage].
 */
suspend fun <T : Any?> safeApiCall(call: suspend () -> Result<T>, errorMessage: String = ""): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        Result.Error(SimpleError("-1", e.message.filterEmpty().ifBlank { errorMessage }))
    }
}

fun String?.filterEmpty(defaultValue: String = ""): String {
    return this ?: defaultValue
}