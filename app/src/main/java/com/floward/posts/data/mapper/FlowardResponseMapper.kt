package com.floward.posts.data.mapper

import com.floward.posts.data.model.PostsResponse
import com.floward.posts.data.model.UsersResponse
import com.floward.posts.domain.model.UserModel


internal fun List<PostsResponse>.toPostsResponseUseCaseModel(): List<UserModel> {
    return this.map {
        UserModel(
            userId = it.userId.toString(),
            id = it.id.toString(),
            title = it.title,
            body = it.body
        )
    }
}

internal fun List<UsersResponse>.toUserResponseUseCaseModel(): List<UserModel> {
    return this.map {
        UserModel(
            albumId = it.albumId.toString(),
            userId = it.userId.toString(),
            photoUrl = it.url,
            thumbnailUrl = it.thumbnailUrl,
            name = it.name
        )
    }
}