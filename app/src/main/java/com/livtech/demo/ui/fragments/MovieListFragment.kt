package com.livtech.demo.ui.fragments

import androidx.lifecycle.Observer

class MovieListFragment : BaseMovieListFragment() {

    override fun iniMovieList() {
        super.iniMovieList()
        movieListViewModel.movieListData.observe(viewLifecycleOwner, Observer {
            movieListBinding.resource = it
            movieListAdapter.submitList(it.data)
        })
    }
}