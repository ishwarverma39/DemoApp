package com.livtech.demo.core

import android.app.Application
import com.livtech.common.core.network.ApiClient

class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiClient.initClient(AppConstants.BASE_URL, ApiAuthenticator.authInterceptor)
    }
}