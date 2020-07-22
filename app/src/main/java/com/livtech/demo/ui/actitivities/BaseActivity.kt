package com.livtech.demo.ui.actitivities

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity(), BaseActivityLifeCycle {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initOnCreate(savedInstanceState)
    }
    @CallSuper
    override fun initOnCreate(savedInstanceState: Bundle?) {
        initViews()
    }

    override fun setFragment(fragment: Fragment, tag: String, addToBackStack: Boolean) {
        supportFragmentManager.beginTransaction().let {
            it.replace(getMainContainerId(), fragment, tag)
            if (addToBackStack)
                it.addToBackStack(tag)
            it.commit()
        }
    }
}