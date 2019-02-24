package com.test.producthunt

import android.app.Application
import com.test.producthunt.di.AppComponent
import com.test.producthunt.di.AppModule
import com.test.producthunt.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

}