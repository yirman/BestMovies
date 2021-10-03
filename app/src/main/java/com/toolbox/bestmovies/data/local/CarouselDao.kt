package com.toolbox.bestmovies.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.toolbox.bestmovies.data.entities.Carousel
import com.toolbox.bestmovies.data.entities.CarouselWithMovies
import com.toolbox.bestmovies.data.entities.Movie

@Dao
abstract class CarouselDao {

    @Query("DELETE FROM carousels")
    abstract fun deleteAllCarousels();

    @Transaction
    @Query("SELECT * FROM carousels")
    abstract fun queryCarousels(): LiveData<List<CarouselWithMovies>>

    @Query("SELECT * FROM movies")
    abstract fun queryMovies(): LiveData<List<Movie>>

    @Insert(onConflict = REPLACE)
    abstract fun insertCarousel(carousel: Carousel): Long

    @Insert(onConflict = REPLACE)
    abstract fun insertMovies(movie: List<Movie>)

    fun insertCarouselsWithMovies(carousels: List<Carousel>) {
        carousels.forEach { carousel ->
            val carouselId = insertCarousel(carousel)
            val movies = carousel.items
            movies?.let{ list ->
                for(movie in list){
                    movie.carouselId = carouselId
                }
                insertMovies(movies)
            }
        }
    }
}