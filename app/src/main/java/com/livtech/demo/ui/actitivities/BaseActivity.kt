package com.livtech.demo.ui.actitivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOnCreate(savedInstanceState)
    }

    open fun initOnCreate(bundle: Bundle?) {
        setContentView(getLayoutId())
        initViews()
    }

    abstract fun getLayoutId(): Int

    abstract fun initViews()

    open fun setFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
        supportFragmentManager.beginTransaction().let {
            it.replace(getMainContainerId(), fragment, tag)
            if (addToBackStack)
                it.addToBackStack(tag)
            it.commit()
        }
    }

    abstract fun getMainContainerId(): Int
}