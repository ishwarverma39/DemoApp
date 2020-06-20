package com.livtech.demo.core.repos

import androidx.lifecycle.LiveData
import com.livtech.demo.core.apiservices.TmdbApi
import com.livtech.demo.core.database.MovieDao
import com.livtech.common.core.models.BaseRepo
import com.livtech.common.core.models.Resource
import com.livtech.common.core.network.NetworkBoundResource
import com.livtech.demo.core.models.MovieIdDetail
import com.livtech.demo.core.models.TmdbMovie
import com.livtech.demo.core.models.TmdbMovieListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepo(val movieDao: MovieDao, val dispatcher: CoroutineDispatcher) : BaseRepo() {

    fun fetchMovies(): LiveData<Resource<MutableList<TmdbMovie>?>> {
        return object : NetworkBoundResource<MutableList<TmdbMovie>, TmdbMovieListResponse>(
            shouldLoad = true,
            dispatcher = dispatcher
        ) {
            override suspend fun saveApiCallResponse(response: TmdbMovieListResponse?) {
                movieDao.insertMovies(response?.results!!)
            }

            override fun getRequestAsync(): Deferred<Response<TmdbMovieListResponse>> {
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

    fun getMovieIdDetail(movieId: Int): LiveData<Resource<TmdbMovie?>> {
        return object : NetworkBoundResource<TmdbMovie, MovieIdDetail>() {
            override fun getRequestAsync(): Deferred<Response<MovieIdDetail>> {
                return apiService(TmdbApi::class.java).getMovieIdDetailsByIdAsync(movieId)
            }

            override fun loadFromDb(): LiveData<TmdbMovie> {
                return movieDao.getMovieById(movieId)
            }

            override suspend fun saveApiCallResponse(response: MovieIdDetail?) {
                response?.let { movieDao.updateMovieIdDetail(it, movieId) }
            }
        }.asLiveData
    }
}