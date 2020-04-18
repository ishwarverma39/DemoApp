package com.livtech.indihood.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.livtech.indihood.R
import com.livtech.indihood.ui.fragments.ContentDetailFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initContentDetailFragment()
    }

    private fun initContentDetailFragment() {
        val contentDetailFragment = ContentDetailFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, contentDetailFragment, "CONTENT_DETAIL_FRAGMENT").commit()
    }
}
