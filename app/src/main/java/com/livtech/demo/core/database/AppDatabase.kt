package com.example.kotlintest.core.database

import android.content.Context
import androidx.room.*
import com.example.kotlintest.core.TmdbMovie
import com.livtech.demo.core.database.ModelTypeConverter

const val DB_NAME = "todo_database"
const val DB_VERSION = 1

@Database(
    entities = arrayOf(TmdbMovie::class),
    version = DB_VERSION,
    exportSchema = false
)
@TypeConverters(ModelTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}


