package com.livtech.demo.core

import com.livtech.demo.core.models.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface ApiServices {
    @POST("login")
    fun loginAsync(@QueryMap params: HashMap<String, String>): Deferred<Response<LoginResponse>>
}