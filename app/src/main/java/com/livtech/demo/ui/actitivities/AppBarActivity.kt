package com.livtech.demo.ui.actitivities

import androidx.annotation.CallSuper
import androidx.appcompat.widget.Toolbar
import com.livtech.demo.R

abstract class AppBarActivity : BaseActivity(), AppBarActivityLifeCycle {
    open var toolbar: Toolbar? = null

    @CallSuper
    override fun initViews() {
        initToolBar()
    }

    override fun initToolBar() {
        toolbar = findViewById(getToolBarId())
        setSupportActionBar(toolbar)
        title = getTitleText()
    }

    override fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.let {
            it.setNavigationIcon(R.drawable.ic_action_back)
            it.setNavigationOnClickListener { onBackArrowPress() }
        }
    }

    override fun onBackArrowPress() {
        onBackPressed()
    }

    override fun getToolBarId(): Int {
        return R.id.toolbar
    }

    override fun getLayout(): Int {
        return R.layout.activity_app_bar
    }

    override fun getMainContainerId(): Int {
        return R.id.mainContainer
    }
}