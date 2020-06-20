package com.example.kotlintest.core

import androidx.lifecycle.LiveData
import com.example.kotlintest.core.database.MovieDao
import com.livtech.common.core.models.BaseRepo
import com.livtech.common.core.models.Resource
import com.livtech.common.core.network.NetworkBoundResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepo(val movieDao: MovieDao, val dispatcher: CoroutineDispatcher) : BaseRepo() {

    fun fetchMovies(): LiveData<Resource<MutableList<TmdbMovie>?>> {
        return object : NetworkBoundResource<MutableList<TmdbMovie>, TmdbMovieResponse>(
            shouldLoad = true,
            dispatcher = dispatcher
        ) {
            override suspend fun saveApiCallResponse(response: TmdbMovieResponse?) {
                movieDao.insertMovies(response?.results!!)
            }

            override fun getRequestAsync(): Deferred<Response<TmdbMovieResponse>> {
                return apiService(TmdbApi::class.java).getPopularMovieAsync()
            }

            override fun loadFromDb(): LiveData<MutableList<TmdbMovie>> {
                return movieDao.fetchMovies()
            }

        }.asLiveData
    }

    fun fetchBookmarkedMovies(bookmarked: Boolean): LiveData<List<TmdbMovie>> {
        return movieDao.getMoviesByBookmark(bookmarked = bookmarked)
    }
}