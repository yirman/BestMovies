package com.toolbox.bestmovies.data.remote

import dagger.Lazy
import javax.inject.Inject

class CarouselRemoteDataSource @Inject constructor(
    var carouselService: Lazy<CarouselService>
): RemoteDataSource(){

    suspend fun requestCarousels() = getResult { carouselService.get().requestCarousels() }

}