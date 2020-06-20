package com.livtech.demo.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.livtech.demo.core.models.MovieIdDetail
import java.lang.reflect.Type

class ModelTypeConverter {
    @TypeConverter
    fun stringMovieIdDetail(data: String): MovieIdDetail {
        val listType: Type = object : TypeToken<MovieIdDetail>() {}.type
        return Gson().fromJson(data, listType)
    }
}