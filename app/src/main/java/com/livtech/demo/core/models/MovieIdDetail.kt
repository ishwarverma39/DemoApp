package com.livtech.demo.core.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieIdDetail(
    var facebook_id: String,
    var id: Int,
    var imdb_id: String,
    var instagram_id: String,
    var twitter_id: String
) : Parcelable