package com.toolbox.bestmovies.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CarouselWithMovies (
    @Embedded var carousel: Carousel,
    @Relation(parentColumn = "id", entityColumn = "carouselId", entity = Movie::class) var movies: List<Movie>
)