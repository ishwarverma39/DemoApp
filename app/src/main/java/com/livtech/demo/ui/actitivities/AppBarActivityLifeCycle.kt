package com.livtech.demo.ui.actitivities

interface AppBarActivityLifeCycle {
    fun initToolBar()
    fun showBackButton()
    fun onBackArrowPress()
    fun getTitleText(): String
    fun getToolBarId(): Int
}