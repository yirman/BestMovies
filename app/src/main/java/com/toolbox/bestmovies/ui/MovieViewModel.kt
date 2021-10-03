package com.toolbox.bestmovies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.toolbox.bestmovies.data.entities.Movie
import com.toolbox.bestmovies.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    repository: MovieRepository
): ViewModel(){

    private val _id = MutableLiveData<Int>()

    private val _movie = _id.switchMap { id ->
        repository.queryMovie(id)
    }

    val movie: LiveData<Movie> = _movie

    fun queryMovie(id: Int) {
        _id.value = id
    }

}