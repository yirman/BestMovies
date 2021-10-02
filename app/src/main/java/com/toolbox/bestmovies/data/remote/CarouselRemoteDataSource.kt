package com.toolbox.bestmovies.data.remote

class CarouselRemoteDataSource constructor(
    private val carouselService: CarouselService
): RemoteDataSource(){

    suspend fun requestCarousels() = getResult { carouselService.requestCarousels() }

}