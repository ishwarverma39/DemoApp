package com.livtech.demo.core.repos

import androidx.lifecycle.LiveData
import com.livtech.common.core.models.BaseRepo
import com.livtech.common.core.models.Resource
import com.livtech.demo.core.ApiServices
import com.livtech.demo.core.models.LoginRequest
import com.livtech.demo.core.models.LoginResponse

class LoginRepo : BaseRepo() {
    fun doLogin(loginRequest: LoginRequest): LiveData<Resource<LoginResponse?>> {
        val params = HashMap<String, String>().apply {
            put("username", loginRequest.userName)
            put("password", loginRequest.password)
        }
        return makeApiCall(apiService(ApiServices::class.java).loginAsync(params))
    }
}