package com.test.producthunt.presentation.productslist

import android.arch.lifecycle.ViewModel
import com.test.producthunt.App
import com.test.producthunt.domain.GetProductsForTodayUseCase
import com.test.producthunt.domain.GetTopicsUseCase
import com.test.producthunt.domain.Product
import com.test.producthunt.domain.Topic
import com.test.producthunt.presentation.common.Action
import com.test.producthunt.presentation.common.Lce
import com.test.producthunt.presentation.common.ViewStateStore
import javax.inject.Inject

class ProductsViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var getProductsForTodayUseCase: GetProductsForTodayUseCase

    @Inject
    lateinit var getTopicsUseCase: GetTopicsUseCase

    val productsStore = ViewStateStore(Lce.Loading as Lce<List<Product>>)
    val topicsStore = ViewStateStore(Lce.Loading as Lce<List<Topic>>)
    val currentTopicStore = ViewStateStore(Lce.Loading as Lce<Topic>)

    fun updateProducts(topicName: String) {
        if (topicsStore.state() is Lce.Success) {
            topicsStore.state().data?.let { list ->
                list.find { topic -> topic.name == topicName }
                    ?.let { topic -> productsStore.dispatchAction { getProductsForTodayUseCase.run(topic.slug) } }
            }
        }
    }

    fun updateTopics() {
        topicsStore.dispatchAction { getTopicsUseCase.run() }
    }

    fun updateCurrentTopic(topicName: String) {
        topicsStore.state().data?.let { list ->
            list.find { topic -> topic.name == topicName }
                ?.let { topic -> currentTopicStore.dispatchAction { Action { Lce.Success(topic) } } }
        }
    }
}
