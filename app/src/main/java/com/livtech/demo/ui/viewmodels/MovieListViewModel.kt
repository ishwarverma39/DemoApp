package com.livtech.demo.ui.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.livtech.common.core.utils.DefaultDispatcherProvider
import com.livtech.demo.core.database.AppDatabase
import com.livtech.demo.core.models.TmdbMovie
import com.livtech.demo.core.repos.MovieRepo
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repo: MovieRepo = MovieRepo(
        AppDatabase.INSTANCE!!.movieDao(),
        DefaultDispatcherProvider().io()
    )
) : ViewModel(), LifecycleObserver {

    val movieListData =
        repo.fetchMovies()

    fun onBookMarkClick(tmdbMovie: TmdbMovie) {
        viewModelScope.launch(DefaultDispatcherProvider().io()) {
            repo.updateBookmark(tmdbMovie)
        }
    }

    val bookmarkedMovieList =
        repo.fetchBookmarkedMovies(true)
}