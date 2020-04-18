package com.livtech.indihood.core.apiservices

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface IndihoodApiServices {
    @GET
    fun getSchema(@Url url: String): Deferred<Response<String>>

    @GET
    fun getRecords(@Url url: String): Deferred<Response<String>>

}