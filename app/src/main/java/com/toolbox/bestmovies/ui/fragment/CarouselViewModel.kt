package com.toolbox.bestmovies.ui.fragment

import androidx.lifecycle.ViewModel
import com.toolbox.bestmovies.data.repository.CarouselRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarouselViewModel @Inject constructor(
    repository: CarouselRepository
) : ViewModel() {
    val carousels = repository.getCarousels()
}