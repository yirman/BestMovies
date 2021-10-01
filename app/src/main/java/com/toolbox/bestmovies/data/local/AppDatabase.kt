package com.toolbox.bestmovies.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.toolbox.bestmovies.data.entities.Auth
import com.toolbox.bestmovies.data.entities.Carousel
import com.toolbox.bestmovies.data.entities.Movie

@Database(entities = [Carousel::class, Movie::class, Auth::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carouselDao(): CarouselDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "movies")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

}