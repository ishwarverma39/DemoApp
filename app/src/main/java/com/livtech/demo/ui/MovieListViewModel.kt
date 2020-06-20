package com.livtech.demo.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.livtech.demo.core.repos.MovieRepo
import com.livtech.common.core.utils.DefaultDispatcherProvider
import com.livtech.demo.core.database.AppDatabase

class MovieListViewModel(
    private val repo: MovieRepo = MovieRepo(
        AppDatabase.INSTANCE!!.movieDao(),
        DefaultDispatcherProvider().io()
    )
) : ViewModel(), LifecycleObserver {

    val movieListData =
        repo.fetchMovies()

}