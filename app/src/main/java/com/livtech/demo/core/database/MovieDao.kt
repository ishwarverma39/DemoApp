package com.example.kotlintest.core.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlintest.core.TmdbMovie
import com.livtech.demo.core.models.MovieIdDetail

@Dao
interface MovieDao {
    @Query("select * from movies")
    fun fetchMovies(): LiveData<MutableList<TmdbMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: MutableList<TmdbMovie>)

    @Query("update movies set `bookmarked` = :bookmarked where `id` =:id")
    suspend fun updateBookmark(id: Int, bookmarked: Boolean)

    @Query("select * from movies where bookmarked=:bookmarked")
    fun getMoviesByBookmark(bookmarked: Boolean): LiveData<List<TmdbMovie>>

    @Query("update movies set `movieIdDetail` =:movieIdDetail where `id`=:id")
    suspend fun updateMovieIdDetail(movieIdDetail: MovieIdDetail, id: Int)

}