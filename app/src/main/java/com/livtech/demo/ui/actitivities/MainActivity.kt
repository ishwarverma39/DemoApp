package com.livtech.demo.ui.actitivities

import com.livtech.demo.R
import com.livtech.demo.ui.fragments.MessageThreadListFragment

class MainActivity : AppBarActivity() {

    override fun getTitleText(): String {
        return getString(R.string.app_name)
    }

    override fun initViews() {
        super.initViews()
        val fragment = MessageThreadListFragment()
        setFragment(fragment = fragment, tag = "MESSAGES")
    }
}