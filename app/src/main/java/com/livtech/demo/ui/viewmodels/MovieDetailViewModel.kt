package com.livtech.demo.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.livtech.common.core.utils.DefaultDispatcherProvider
import com.livtech.demo.core.database.AppDatabase
import com.livtech.demo.core.models.TmdbMovie
import com.livtech.demo.core.repos.MovieRepo
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val repo: MovieRepo = MovieRepo(
        AppDatabase.INSTANCE!!.movieDao(),
        DefaultDispatcherProvider().io()
    )
) : ViewModel() {
    private val _movieIdData = MutableLiveData<Int>()


    val movieDetailData = _movieIdData.switchMap {
        repo.getMovieIdDetail(it)
    }

    fun onBookMarkClick(tmdbMovie: TmdbMovie) {
        viewModelScope.launch(DefaultDispatcherProvider().io()) {
            repo.updateBookmark(tmdbMovie)
        }
    }

    fun fetchMovieDetail(id: Int) {
        _movieIdData.value = id;
    }

}