package com.livtech.demo.core.base

import android.app.Application
import com.example.kotlintest.core.ApiAuthenticator
import com.example.kotlintest.core.AppConstants
import com.livtech.common.core.network.ApiClient

class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiClient.initClient(AppConstants.BASE_URL, ApiAuthenticator.authInterceptor)
    }
}