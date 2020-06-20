package com.livtech.demo.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.livtech.demo.core.models.MovieIdDetail
import java.lang.reflect.Type

class ModelTypeConverter {
    @TypeConverter
    fun stringToMovieIdDetail(data: String): MovieIdDetail? {
        val type: Type = object : TypeToken<MovieIdDetail?>() {}.type
        return Gson().fromJson(data, type)
    }

    @TypeConverter
    fun someMovieIdDetailToString(movieIdDetail: MovieIdDetail?): String? {
        return Gson().toJson(movieIdDetail)
    }
}