package com.test.producthunt.data

import com.google.gson.annotations.SerializedName

data class TopicsResponse(
    @SerializedName("topics")
    val topics: List<TopicResponse>
)

data class TopicResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)
