package com.livtech.indihood.core

import android.app.Application
import com.livtech.common.core.network.ApiClient
import com.livtech.indihood.core.apiservices.ApiAuthenticator

class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiClient.initClient(AppConstants.BASE_URL, ApiAuthenticator.authInterceptor)
    }
}