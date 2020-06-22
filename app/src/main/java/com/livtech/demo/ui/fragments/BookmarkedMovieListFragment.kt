package com.livtech.demo.ui.fragments

import androidx.lifecycle.Observer

class BookmarkedMovieListFragment : BaseMovieListFragment() {

    override fun iniMovieList() {
        super.iniMovieList()
        movieListViewModel.bookmarkedMovieList.observe(viewLifecycleOwner, Observer {
            movieListBinding.resource = it
            movieListAdapter.submitList(it.data)
        })
    }
}