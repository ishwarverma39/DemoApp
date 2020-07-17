package com.livtech.demo.ui.actitivities

import android.os.Bundle
import androidx.fragment.app.Fragment

interface BaseActivityLifeCycle {
    fun initOnCreate(savedInstanceState: Bundle?)
    fun getLayout(): Int
    fun initViews()
    fun getMainContainerId(): Int
    fun setFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = false)
}