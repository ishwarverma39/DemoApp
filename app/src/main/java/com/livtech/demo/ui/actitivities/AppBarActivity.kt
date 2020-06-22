package com.livtech.demo.ui.actitivities

import androidx.annotation.CallSuper
import androidx.appcompat.widget.Toolbar
import com.livtech.demo.R

abstract class AppBarActivity : BaseActivity() {
    open var toolbar: Toolbar? = null

    @CallSuper
    override fun initViews() {
        initToolBar()
    }

    open fun initToolBar() {
        toolbar = findViewById(getToolBarId())
        setSupportActionBar(toolbar)
        title = getTitleText()
    }

    open fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.let {
            it.setNavigationIcon(R.drawable.ic_action_back)
            it.setNavigationOnClickListener { onBackArrowPress() }
        }
    }

    open fun onBackArrowPress() {
        onBackPressed()
    }

    abstract fun getTitleText(): String

    open fun getToolBarId(): Int {
        return R.id.toolbar
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_app_bar
    }
}