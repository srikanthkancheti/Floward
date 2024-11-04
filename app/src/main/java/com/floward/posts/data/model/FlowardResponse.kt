package com.floward.posts.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class PostsResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)

@Keep
internal data class UsersResponse(
    @SerializedName("albumId") val albumId: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
)