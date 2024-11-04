package com.floward.posts.api.network

import androidx.annotation.Keep

@Keep
data class SimpleError(val errorCode: String? = "-1", val errorMessage: String? = "Something went wrong. Please try again later!")