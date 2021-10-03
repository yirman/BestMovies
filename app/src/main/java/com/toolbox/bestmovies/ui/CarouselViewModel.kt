package com.toolbox.bestmovies.ui

import androidx.lifecycle.ViewModel
import com.toolbox.bestmovies.data.repository.CarouselRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarouselViewModel @Inject constructor(
    private val repository: CarouselRepository
) : ViewModel() {
    val characters = repository.getCarousels()
}