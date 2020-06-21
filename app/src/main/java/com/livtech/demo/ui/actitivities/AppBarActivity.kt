package com.livtech.demo.ui.actitivities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.livtech.demo.R

abstract class AppBarActivity : BaseActivity() {
    private var toolbar: Toolbar? = null

    override fun initOnCreate(bundle: Bundle?) {
        super.initOnCreate(bundle)
        initToolBar()
    }

    open fun initToolBar() {
        toolbar = findViewById(getToolBarId())
        setSupportActionBar(toolbar)
        title = getTitleText()
    }

    open fun showBackButton() {
        toolbar?.let {
            it.setNavigationIcon(R.drawable.ic_action_back)
            it.setNavigationOnClickListener { onBackArrowPress() }
        }
    }

    open fun onBackArrowPress() {
        onBackArrowPress()
    }

    abstract fun getTitleText(): String

    open fun getToolBarId(): Int {
        return R.id.toolbar
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_app_bar
    }
}