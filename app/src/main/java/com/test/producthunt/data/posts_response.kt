package com.test.producthunt.data

import com.google.gson.annotations.SerializedName

data class PostsResponse(
    @SerializedName("posts")
    val posts: List<PostResponse>
)

data class PostResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("tagline")
    val description: String,
    @SerializedName("votes_count")
    val upvotes: Int,
    @SerializedName("screenshot_url")
    val screen: ScreenShotResponse,
    @SerializedName("thumbnail")
    val thumb: Thumbnail,
    @SerializedName("discussion_url")
    val productUrl: String
)

data class ScreenShotResponse(
    @SerializedName("850px")
    val url: String
)

data class Thumbnail(
    @SerializedName("image_url")
    val imageUrl: String
)
