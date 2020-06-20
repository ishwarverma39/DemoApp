package com.livtech.demo.ui.actitivities

import com.livtech.demo.R

class MainActivity : AppBarActivity() {
    override fun getTitleText(): String {
        return resources.getString(R.string.app_name)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
    }

    override fun getMainContainerId(): Int {
        return R.id.mainContainer
    }
}