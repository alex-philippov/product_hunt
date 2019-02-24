package com.test.producthunt.di

import com.test.producthunt.presentation.productslist.ProductsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(productsViewModel: ProductsViewModel)

}
