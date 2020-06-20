package com.livtech.demo.core.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    @ColumnInfo(name = "bookmarked", defaultValue = "0")
    var bookmarked: Boolean,
    var movieIdDetail: MovieIdDetail?
) : Parcelable