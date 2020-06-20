package com.livtech.demo.ui.actitivities

import com.livtech.demo.R
import com.livtech.demo.ui.fragments.MovieListFragment

class MainActivity : AppBarActivity() {
    override fun getTitleText(): String {
        return resources.getString(R.string.app_name)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        val fragment = MovieListFragment(R.layout.fragment_movie_list)
        setFragment(fragment, "Movie")
    }

    override fun getMainContainerId(): Int {
        return R.id.mainContainer
    }
}