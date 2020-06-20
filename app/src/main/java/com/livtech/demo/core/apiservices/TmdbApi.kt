package com.livtech.demo.core.apiservices

import com.livtech.demo.core.models.MovieIdDetail
import com.livtech.demo.core.models.TmdbMovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApi {
    @GET("movie/popular")
    fun getPopularMovieAsync(): Deferred<Response<TmdbMovieListResponse>>

    @GET("movie/{id}")
    fun getMovieIdDetailsByIdAsync(@Path("id") id: Int): Deferred<Response<MovieIdDetail>>
}
