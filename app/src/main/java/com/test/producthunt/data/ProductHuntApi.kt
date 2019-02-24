package com.test.producthunt.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductHuntApi {

    @GET("posts/all?")
    fun products(@Query("search[category]") category: String) : Deferred<PostsResponse>

    @GET("topics")
    fun topics() : Deferred<TopicsResponse>
}
