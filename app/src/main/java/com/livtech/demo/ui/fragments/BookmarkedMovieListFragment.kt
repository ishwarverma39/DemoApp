package com.livtech.demo.ui.fragments

import androidx.lifecycle.Observer

class BookmarkedMovieListFragment(layoutId: Int) : BaseMovieListFragment(layoutId) {

    override fun iniMovieList() {
        super.iniMovieList()
        movieListViewModel.bookmarkedMovieList.observe(viewLifecycleOwner, Observer {
            movieListBinding.resource = it
            movieListAdapter.submitList(it.data)
        })
    }
}