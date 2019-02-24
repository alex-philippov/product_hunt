package com.test.producthunt.data

import com.test.producthunt.BuildConfig
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class ProductHuntAuthenticator : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request().newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.PRODUCT_HUNT_ACCESS_TOKEN}")
            .build()
    }

}