package com.livtech.demo.core

import okhttp3.Interceptor

object ApiAuthenticator {

    //Creating Auth Interceptor to refresh the access token when it gets expired
    val authInterceptor = Interceptor { chain ->
        val newRequest = chain.request()
            .newBuilder()
            .build()

        chain.proceed(newRequest)
    }
}