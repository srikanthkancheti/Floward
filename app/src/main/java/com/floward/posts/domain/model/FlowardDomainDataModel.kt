package com.floward.posts.domain.model

data class UserModel(
    val albumId: String? = null,
    val userId: String? = null,
    var photoUrl: String? = null,
    var thumbnailUrl: String? = null,
    var name: String? = null,
    val id: String? = null,
    val title: String? = null,
    val body: String? = null,
    var postsCount: Int = 0
) {
    fun getPostsCount(): String {
        return "Posts count: $postsCount"
    }
}
