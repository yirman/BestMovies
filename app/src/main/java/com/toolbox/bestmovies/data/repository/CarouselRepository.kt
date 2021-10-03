package com.toolbox.bestmovies.data.repository

import androidx.lifecycle.LiveData
import com.toolbox.bestmovies.data.entities.CarouselWithMovies
import com.toolbox.bestmovies.data.local.CarouselDao
import com.toolbox.bestmovies.data.remote.CarouselRemoteDataSource
import com.toolbox.bestmovies.data.remote.Resource
import com.toolbox.bestmovies.utils.performGetOperation
import javax.inject.Inject

class CarouselRepository @Inject constructor(
    private val remoteDataSource: CarouselRemoteDataSource,
    private val localDataSource: CarouselDao
) {


    fun getCarousels(): LiveData<Resource<List<CarouselWithMovies>>> = performGetOperation(
        databaseQuery = { localDataSource.queryCarousels() },
        networkCall = { remoteDataSource.requestCarousels() },
        saveCallResult = { localDataSource.insertCarouselsWithMovies(it) }
    )
}