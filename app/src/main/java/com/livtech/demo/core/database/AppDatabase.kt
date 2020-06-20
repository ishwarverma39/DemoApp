package com.livtech.demo.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.livtech.demo.core.models.TmdbMovie

const val DB_NAME = "embibe_db"
const val DB_VERSION = 1

@Database(
    entities = [TmdbMovie::class],
    version = DB_VERSION,
    exportSchema = false
)
@TypeConverters(ModelTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun init(context: Context) {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
                INSTANCE = instance
            }
        }
    }
}


