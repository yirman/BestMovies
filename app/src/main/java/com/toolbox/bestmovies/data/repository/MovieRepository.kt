package com.toolbox.bestmovies.data.repository

import androidx.lifecycle.LiveData
import com.toolbox.bestmovies.data.entities.Movie
import com.toolbox.bestmovies.data.local.MovieDao
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val localDataSource: MovieDao
) {

    fun queryMovie(id: Int): LiveData<Movie> = localDataSource.queryMovie(id)
}