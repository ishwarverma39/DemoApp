package com.livtech.demo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModels()
        return bindView(inflater, container, savedInstanceState)
    }

    open fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun initViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view, savedInstanceState)
    }

    abstract fun initViews(view: View, savedInstanceState: Bundle?)

}