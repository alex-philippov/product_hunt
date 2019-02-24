package com.test.producthunt.domain

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import com.test.producthunt.data.ProductHuntNetworkService
import com.test.producthunt.presentation.common.Action
import com.test.producthunt.presentation.common.Lce

class GetTopicsUseCase(private val networkService: ProductHuntNetworkService) {

    suspend fun run() : Action<Lce<List<Topic>>> {
        val result = networkService.topics()
        return Action {
            when (result) {
                is Ok -> Lce.Success(result.get()!!)
                is Err -> Lce.Error(result.getError()!!)
            }
        }
    }
}
