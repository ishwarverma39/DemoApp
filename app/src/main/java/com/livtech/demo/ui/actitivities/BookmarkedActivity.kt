package com.livtech.demo.ui.actitivities

import com.livtech.demo.R
import com.livtech.demo.ui.fragments.BookmarkedMovieListFragment

class BookmarkedActivity : AppBarActivity() {

    override fun getTitleText(): String {
        return resources.getString(R.string.bookmarked_title)
    }

    override fun initViews() {
        super.initViews()
        val fragment = BookmarkedMovieListFragment()
        setFragment(fragment, "Movie")
        showBackButton()
    }

    override fun getMainContainerId(): Int {
        return R.id.mainContainer
    }
}