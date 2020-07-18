package com.livtech.demo.ui.fragments

import android.os.Bundle
import android.view.View
import com.livtech.demo.R
import com.livtech.demo.databinding.FragmentLoginBinding

class LoginFragment : ViewDataBindingBaseFragment<FragmentLoginBinding>() {
    override fun getLayout(): Int {
        return R.layout.fragment_login
    }

    override fun initViewModels() {

    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {

    }
}