package com.test.producthunt.domain

import com.github.michaelbull.result.*
import com.test.producthunt.data.ProductHuntNetworkService
import com.test.producthunt.presentation.common.Action
import com.test.producthunt.presentation.common.Lce

class GetProductsForTodayUseCase(private val networkService: ProductHuntNetworkService) {

    suspend fun run(category: String): Action<Lce<List<Product>>> {
        val result = networkService.posts(category)
        return Action {
            when (result) {
                is Ok -> Lce.Success(result.get()!!)
                is Err -> Lce.Error(result.getError()!!)
            }
        }
    }
}
