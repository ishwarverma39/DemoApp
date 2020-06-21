package com.livtech.demo.ui.actitivities

import com.livtech.demo.R
import com.livtech.demo.ui.fragments.MovieDetailFragment

class MovieDetailActivity : AppBarActivity() {

    override fun getTitleText(): String {
        return getString(R.string.movie_detail)
    }

    override fun initViews() {
        val fragment = MovieDetailFragment(R.layout.fragment_movie_detail)
        fragment.arguments = intent.extras
        setFragment(fragment, "MOVIE_DETAIL")
    }

    override fun getMainContainerId(): Int {
        return R.id.mainContainer
    }
}