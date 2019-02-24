package com.test.producthunt.presentation.common

class Action<T>(private val f: T.() -> T) {
    operator fun invoke(t: T) = t.f()
}