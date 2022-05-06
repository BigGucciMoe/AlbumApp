package com.app.album

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DataEntity::class], version = 1)
abstract class database : RoomDatabase() {
    abstract fun dataDao(): DataDao
    companion object {
        @Volatile
        private var INSTANCE: database? = null
        fun getInstance(context: Context): database {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    database::class.java,
                    "data")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as database
        }
    }
}