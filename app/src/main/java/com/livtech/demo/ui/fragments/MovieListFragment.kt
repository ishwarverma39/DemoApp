package com.livtech.demo.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.livtech.demo.databinding.FragmentMovieListBinding
import com.livtech.demo.ui.MovieListViewModel

class MovieListFragment(layoutId: Int) : BaseFragment<FragmentMovieListBinding>(layoutId) {
    lateinit var movieListBinding: FragmentMovieListBinding
    lateinit var movieListViewModel: MovieListViewModel

    override fun initViewModels() {
        movieListViewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(
            MovieListViewModel::class.java
        )
    }

    override fun bindView(viewDataBinding: FragmentMovieListBinding) {
        movieListBinding = viewDataBinding
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        iniMovieList()
    }

    private fun iniMovieList() {
        movieListViewModel.movieListData.observe(viewLifecycleOwner, Observer {
            movieListBinding.resource = it
        })
    }
}