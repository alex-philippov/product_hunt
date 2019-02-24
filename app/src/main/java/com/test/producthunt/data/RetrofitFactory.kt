package com.test.producthunt.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.producthunt.BuildConfig
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory(private val authenticator: Authenticator) {

    fun build() = Retrofit.Builder()
        .baseUrl(BuildConfig.PRODUCT_HUNT_BASE_URL)
        .client(buildClient())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun buildClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .authenticator(authenticator)
            .build()
    }
}
