package com.livtech.demo.core.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieIdDetail(
    @SerializedName("facebook_id")
    var facebookId: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("imdb_id")
    var imdbId: String,
    @SerializedName("instagram_id")
    var instagramId: String,
    @SerializedName("twitter_id")
    var twitterId: String
) : Parcelable