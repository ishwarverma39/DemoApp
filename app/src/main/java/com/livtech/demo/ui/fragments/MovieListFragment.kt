package com.livtech.demo.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.livtech.demo.R
import com.livtech.demo.core.models.TmdbMovie
import com.livtech.demo.databinding.FragmentMovieListBinding
import com.livtech.demo.ui.MovieListViewModel
import com.livtech.demo.ui.adapters.MovieListAdapter
import com.livtech.demo.ui.utils.ItemDividerDecoration

class MovieListFragment(layoutId: Int) : BaseFragment<FragmentMovieListBinding>(layoutId) {
    lateinit var movieListBinding: FragmentMovieListBinding
    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var movieListAdapter: MovieListAdapter

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
        movieListAdapter =
            MovieListAdapter(this::onMovieItemClick, this::onBookMarkClick)
        movieListBinding.movieRecycleView.adapter = movieListAdapter
        movieListBinding.movieRecycleView.addItemDecoration(
            ItemDividerDecoration(
                context?.resources?.getDimensionPixelSize(
                    R.dimen.list_item_divider_height
                )
            )
        )
        movieListViewModel.movieListData.observe(viewLifecycleOwner, Observer {
            movieListBinding.resource = it
            movieListAdapter.submitList(it.data)
        })
    }

    private fun onMovieItemClick(tmdbMovie: TmdbMovie) {
        //todo
    }

    private fun onBookMarkClick(tmdbMovie: TmdbMovie, position: Int) {
        tmdbMovie.bookmarked = !tmdbMovie.bookmarked
        movieListAdapter.notifyItemChanged(position)
        movieListViewModel.onBookMarkClick(tmdbMovie)
    }
}