package com.toolbox.bestmovies.data.remote

import com.toolbox.bestmovies.data.entities.Carousel
import retrofit2.Response
import retrofit2.http.GET

interface CarouselService {

    @GET("data")
    suspend fun requestCarousels(): Response<List<Carousel>>

}