package com.livtech.demo.core.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.livtech.common.core.models.BaseRepo
import com.livtech.common.core.models.Resource
import com.livtech.common.core.network.AE_404
import com.livtech.common.core.network.ApiError
import com.livtech.common.core.network.NetworkBoundResource
import com.livtech.demo.core.apiservices.TmdbApi
import com.livtech.demo.core.database.AppDatabase
import com.livtech.demo.core.database.MovieDao
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
                deleteOldMoviesAndSaveNewMovies(response?.results)
            }

            override fun getRequestAsync(): Deferred<Response<TmdbMovieListResponse>> {
                return apiService(TmdbApi::class.java).getPopularMovieAsync()
            }

            override fun loadFromDb(): LiveData<MutableList<TmdbMovie>> {
                return movieDao.fetchMovies()
            }

        }.asLiveData
    }

    fun fetchBookmarkedMovies(bookmarked: Boolean): LiveData<Resource<MutableList<TmdbMovie>?>> {
        return liveData {
            emitSource(
                movieDao.getMoviesByBookmark(bookmarked).map {
                    if (it.isEmpty()) {
                        Resource.Failure(ApiError(AE_404, "No movie bookmarked yet"), null)
                    } else
                        Resource.Success(it)
                }
            )
        }
    }

    fun getMovieIdDetail(movieId: Int): LiveData<Resource<TmdbMovie?>> {
        var idDetail: MovieIdDetail? = null
        return object : NetworkBoundResource<TmdbMovie, MovieIdDetail>() {
            override fun getRequestAsync(): Deferred<Response<MovieIdDetail>> {
                return apiService(TmdbApi::class.java).getMovieIdDetailsByIdAsync(movieId)
            }

            override fun loadFromDb(): LiveData<TmdbMovie> {
                return movieDao.getMovieById(movieId).map { movie ->
                    idDetail = movie.movieIdDetail
                    movie
                }
            }

            override suspend fun saveApiCallResponse(response: MovieIdDetail?) {
                response?.let { movieDao.updateMovieIdDetail(it, movieId) }
            }

            override fun shouldFetch(): Boolean {
                return idDetail == null
            }
        }.asLiveData
    }

    fun updateBookmark(tmdbMovie: TmdbMovie) {
        movieDao.updateBookmark(tmdbMovie.id, tmdbMovie.bookmarked)
    }

    private fun deleteOldMoviesAndSaveNewMovies(newMovies: MutableList<TmdbMovie>?) {
        if (newMovies.isNullOrEmpty()) {
            movieDao.deleteAll()
            return
        }
        AppDatabase.INSTANCE?.runInTransaction {
            val newIds = newMovies.map { it.id }
            movieDao.deleteMoviesIfNotInIds(newIds)
            val oldMovies = movieDao.getOldBookmarkedMeetings(true)
            newMovies.forEach { newMovie ->
                val oldMovie = oldMovies?.find { it.id == newMovie.id }
                oldMovie?.let {
                    if (it.id == newMovie.id) {
                        newMovie.bookmarked = it.bookmarked
                        newMovie.movieIdDetail = it.movieIdDetail
                    }
                }
            }
            movieDao.insertMovies(newMovies)
        }
    }
}