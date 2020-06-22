package com.livtech.demo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModels()
        return bindView(inflater, container, savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    open fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<VB>(inflater, getLayoutId(), container, false)
        bindView(binding)
        return binding.root
    }

    open fun bindView(viewDataBinding: VB) {}

    abstract fun initViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view, savedInstanceState)
    }

    abstract fun initViews(view: View, savedInstanceState: Bundle?)

}