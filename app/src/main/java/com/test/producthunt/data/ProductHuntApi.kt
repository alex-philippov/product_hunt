package com.test.producthunt.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductHuntApi {

    @GET("categories/{category}/posts")
    fun products(@Path("category") category: String) : Deferred<PostsResponse>

    @GET("topics")
    fun topics() : Deferred<TopicsResponse>
}
