package com.livtech.demo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface BaseFragmentLifeCycle {
    /**
     * Use this method to return the layout id for the fragment
     */
    fun getLayout(): Int

    /**
     * Use this method to initialize view models
     */
    fun initViewModels()

    /**
     * This method will be called from onViewCreated method to make actions once the view is created
     */
    fun initViews(view: View, savedInstanceState: Bundle?)

    /**
     * This method binds the layout to the fragment
     */
    fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    /**
     * Override this method to initialize data from arguments
     */
    fun initOnCreate(savedInstanceState: Bundle?)
}