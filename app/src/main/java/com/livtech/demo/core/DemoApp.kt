package com.livtech.demo.core

import android.app.Application
import com.livtech.common.core.network.ApiClient
import com.livtech.demo.BuildConfig

class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiClient.initClient(AppConstants.BASE_URL, AppInterceptor())
        PreferenceManager.initPreferences(context = this)
    }
}