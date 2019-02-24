package com.test.producthunt.presentation.common

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

class ViewStateStore<T : Any>(
        initialState: T
) : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext =
            job + Dispatchers.IO

    private val liveData = MutableLiveData<T>().apply {
        value = initialState
    }

    fun observe(owner: LifecycleOwner, observer: (T) -> Unit) =
            liveData.observe(owner, Observer { observer(it!!) })

    private fun dispatchState(state: T) {
        liveData.value = state
    }

    fun dispatchAction(f: suspend (T) -> Action<T>) {
        launch {
            val action = f(state())
            withContext(Main) {
                dispatchState(action(state()))
            }
        }
    }

    @ObsoleteCoroutinesApi
    fun dispatchActions(channel: ReceiveChannel<Action<T>>) {
        launch {
            channel.consumeEach { action ->
                withContext(Dispatchers.Main) {
                    dispatchState(action(state()))
                }
            }
        }
    }

    fun state() = liveData.value!!

    fun cancel() = job.cancel()
}
