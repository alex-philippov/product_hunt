package com.test.producthunt.data

import com.github.michaelbull.result.Result
import com.test.producthunt.domain.Product
import com.test.producthunt.domain.Topic

class ProductHuntNetworkService(
    private val productHuntApi: ProductHuntApi
) {

    suspend fun posts(category: String) : Result<List<Product>, Throwable>
            = productHuntApi.products(category).getResult().mapResponse { map(it) }

    suspend fun topics() : Result<List<Topic>, Throwable>
            = productHuntApi.topics().getResult().mapResponse { map(it) }

}
