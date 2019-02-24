package com.test.producthunt.data

import com.test.producthunt.domain.Product
import com.test.producthunt.domain.Topic

fun map(postsResponse: PostsResponse) = postsResponse.posts.map {
    Product(
        name = it.name,
        description = it.description,
        upvotes = it.upvotes,
        screenShotUrl = it.screen.url,
        thumbUrl = it.thumb.imageUrl,
        productUrl = it.productUrl
    )
}

fun map(topicsResponse: TopicsResponse) = topicsResponse.topics.map {
    Topic(
        name = it.name,
        slug = it.slug
    )
}