package com.test.producthunt.domain

import java.io.Serializable

data class Product(
    val name: String,
    val description: String,
    val upvotes: Int,
    val thumbUrl: String,
    val screenShotUrl: String,
    val productUrl: String
) : Serializable