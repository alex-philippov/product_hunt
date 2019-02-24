package com.test.producthunt.di

import android.content.Context
import com.test.producthunt.data.ProductHuntApi
import com.test.producthunt.data.ProductHuntAuthenticator
import com.test.producthunt.data.ProductHuntNetworkService
import com.test.producthunt.data.RetrofitFactory
import com.test.producthunt.domain.GetProductsForTodayUseCase
import com.test.producthunt.domain.GetTopicsUseCase
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import retrofit2.Retrofit

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    fun provideAuthenticator() : Authenticator = ProductHuntAuthenticator()

    @Provides
    fun provideRetrofit(authenticator: Authenticator) = RetrofitFactory(authenticator).build()

    @Provides
    fun provideProductHuntApi(retrofit: Retrofit) = retrofit.create(ProductHuntApi::class.java)

    @Provides
    fun provideProductHuntNetworkService(api: ProductHuntApi) = ProductHuntNetworkService(api)

    @Provides
    fun provideGetProductsForTodayUseCase(networkService: ProductHuntNetworkService)
            = GetProductsForTodayUseCase(networkService)

    @Provides
    fun provideGetTopicsUseCase(networkService: ProductHuntNetworkService)
            = GetTopicsUseCase(networkService)

}
