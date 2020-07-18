package com.livtech.demo.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.livtech.common.core.network.NetworkStatus
import com.livtech.demo.R
import com.livtech.demo.core.AppConstants
import com.livtech.demo.core.PreferenceManager
import com.livtech.demo.core.models.LoginRequest
import com.livtech.demo.databinding.FragmentLoginBinding
import com.livtech.demo.ui.actitivities.MainActivity
import com.livtech.demo.ui.viewmodels.LoginViewModel

class LoginFragment : ViewDataBindingBaseFragment<FragmentLoginBinding>() {
    private var loginViewModel: LoginViewModel? = null
    private val loginRequest = LoginRequest(userName = "", password = "")

    override fun getLayout(): Int {
        return R.layout.fragment_login
    }

    override fun initViewModels() {
        loginViewModel = ViewModelProvider(
            viewModelStore,
            defaultViewModelProviderFactory
        ).get(LoginViewModel::class.java)
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        viewDataBinding.loginRequest = loginRequest
        viewDataBinding.loginBtn.setOnClickListener { loginViewModel?.doLogin(loginRequest) }
        initLoginResponse()
    }

    private fun initLoginResponse() {
        loginViewModel?.loginResponse?.observe(viewLifecycleOwner, Observer {
            viewDataBinding.resource = it
            if (it.status == NetworkStatus.SUCCESS) {
                PreferenceManager.saveBoolValue(AppConstants.IS_LOGGED_IN, true)
                startActivity(Intent(context, MainActivity::class.java))
                activity?.finish()
            }
        })
    }
}