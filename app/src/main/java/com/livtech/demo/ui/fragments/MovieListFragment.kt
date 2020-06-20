package com.livtech.demo.ui.fragments

import androidx.lifecycle.Observer

class MovieListFragment(layoutId: Int) : BaseMovieListFragment(layoutId) {

    override fun iniMovieList() {
        super.iniMovieList()
        movieListViewModel.movieListData.observe(viewLifecycleOwner, Observer {
            movieListBinding.resource = it
            movieListAdapter.submitList(it.data)
        })
    }
}