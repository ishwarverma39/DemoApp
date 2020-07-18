package com.livtech.demo.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.livtech.common.core.models.Resource
import com.livtech.common.core.network.ApiError
import com.livtech.common.core.network.UN_KNOWN_ERROR
import com.livtech.demo.core.models.LoginRequest
import com.livtech.demo.core.repos.LoginRepo

class LoginViewModel(private val repo: LoginRepo = LoginRepo()) : ViewModel() {
    private val _logInRequest = MutableLiveData<LoginRequest>()

    val loginResponse = _logInRequest.switchMap {
        if (it.userName.isEmpty() || it.password.isEmpty()) {
            liveData {
                Resource.Failure(
                    ApiError(UN_KNOWN_ERROR, "Please enter valid user name or password", false),
                    null
                )
            }
        } else
            repo.doLogin(it)
    }

    fun doLogin(loginRequest: LoginRequest) {
        _logInRequest.value = loginRequest
    }
}