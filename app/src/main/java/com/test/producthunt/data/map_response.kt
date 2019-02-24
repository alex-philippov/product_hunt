package com.test.producthunt.data

import com.github.michaelbull.result.*
import com.github.michaelbull.result.Ok
import kotlinx.coroutines.Deferred

suspend fun <T> Deferred<T>.getResult(): Result<T, Exception> = Result.of {
    this.await()
}

fun <V, E, R> Result<V, E>.mapResponse(mapper: (response: V) -> R) : Result<R, Throwable> {
    return when (this) {
        is Ok -> Ok(mapper(this.value))
        is Err -> Err(this.error as Throwable)
    }
}
