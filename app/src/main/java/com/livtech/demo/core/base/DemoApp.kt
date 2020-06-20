package com.livtech.demo.core.base

import android.app.Application
import com.livtech.common.core.network.ApiClient
import com.livtech.demo.core.database.AppDatabase

class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiClient.initClient(AppConstants.BASE_URL, ApiAuthenticator.authInterceptor)
        AppDatabase.init(this)
    }
}