package com.livtech.demo.core

import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request().newBuilder()
        val loggedIn = PreferenceManager.getBoolean(AppConstants.IS_LOGGED_IN, false)
        if (loggedIn) {
            original
                .addHeader(
                    AppConstants.HEADER_ACCESS_TOKEN_KEY,
                    PreferenceManager.getStringValue(AppConstants.ACCESS_TOKE_KEY) ?: ""
                )
        }
        return chain.proceed(original.build())
    }
}