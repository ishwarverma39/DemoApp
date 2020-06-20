package com.example.kotlintest.core

import okhttp3.Interceptor

object ApiAuthenticator {

    //Creating Auth Interceptor to add api_key query in front of all the requests.
    val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", AppConstants.tmdbApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }
}