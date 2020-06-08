package com.livtech.demo.core

import okhttp3.Interceptor

object ApiAuthenticator {

    //Creating Auth Interceptor to add api_key query in front of all the requests.
    val authInterceptor = Interceptor { chain ->
        val newRequest = chain.request()
            .newBuilder()
            .build()

        chain.proceed(newRequest)
    }
}