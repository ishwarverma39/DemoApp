package com.livtech.demo.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.livtech.demo.core.models.TmdbMovie
import com.livtech.demo.databinding.FragmentMovieListBinding
import com.livtech.demo.ui.actitivities.MovieDetailActivity
import com.livtech.demo.ui.adapters.MovieListAdapter
import com.livtech.demo.ui.viewmodels.MovieListViewModel

open class BaseMovieListFragment(layoutId: Int) : BaseFragment<FragmentMovieListBinding>(layoutId) {
    lateinit var movieListBinding: FragmentMovieListBinding
    open lateinit var movieListViewModel: MovieListViewModel
    open lateinit var movieListAdapter: MovieListAdapter

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

    open fun iniMovieList() {
        movieListAdapter =
            MovieListAdapter(this::onMovieItemClick, this::onBookMarkClick)
        movieListBinding.movieRecycleView.adapter = movieListAdapter
    }

    private fun onMovieItemClick(tmdbMovie: TmdbMovie) {
        val bundle = bundleOf(pairs = *arrayOf(Pair("MOVIE_ID", tmdbMovie.id)))
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun onBookMarkClick(tmdbMovie: TmdbMovie, position: Int) {
        tmdbMovie.bookmarked = !tmdbMovie.bookmarked
        movieListAdapter.notifyItemChanged(position)
        movieListViewModel.onBookMarkClick(tmdbMovie)
    }
}