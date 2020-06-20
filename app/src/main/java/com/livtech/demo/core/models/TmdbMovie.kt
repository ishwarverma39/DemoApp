package com.example.kotlintest.core

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.livtech.demo.core.models.MovieIdDetail
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class TmdbMovie(
    @PrimaryKey
    val id: Int,
    val vote_average: Double,
    val title: String,
    val overview: String,
    val adult: Boolean,
    @ColumnInfo(defaultValue = "0")
    var bookmarked: Boolean,
    var movieIdDetail: MovieIdDetail?
) : Parcelable