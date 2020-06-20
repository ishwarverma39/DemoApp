package com.livtech.demo.core.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livtech.demo.core.models.MovieIdDetail
import com.livtech.demo.core.models.TmdbMovie

@Dao
interface MovieDao {
    @Query("select * from movies")
    fun fetchMovies(): LiveData<MutableList<TmdbMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<TmdbMovie>)

    @Query("update movies set `bookmarked` =:bookmarked where `id` =:id")
    fun updateBookmark(id: Int, bookmarked: Boolean)

    @Query("select * from movies where bookmarked=:bookmarked")
    fun getMoviesByBookmark(bookmarked: Boolean): LiveData<List<TmdbMovie>>

    @Query("update movies set `movieIdDetail` =:movieIdDetail where `id`=:id")
    fun updateMovieIdDetail(movieIdDetail: MovieIdDetail, id: Int)

    @Query("select * from movies where `id`=:id")
    fun getMovieById(id: Int): LiveData<TmdbMovie>

}