package com.toolbox.bestmovies.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.toolbox.bestmovies.data.entities.Movie

@Dao
abstract class MovieDao {

    @Query("SELECT * FROM movies")
    abstract fun queryAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE carouselId = :carouselId")
    abstract fun queryMoviesByCarousel(carouselId: Int): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE id = :id")
    abstract fun queryMovie(id: Int): LiveData<Movie>

}