package com.toolbox.bestmovies.di

import com.toolbox.bestmovies.data.local.CarouselDao
import com.toolbox.bestmovies.data.remote.CarouselRemoteDataSource
import com.toolbox.bestmovies.data.repository.CarouselRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideCarouselRepository(remoteDataSource: CarouselRemoteDataSource, carouselDao: CarouselDao) =
        CarouselRepository(remoteDataSource, carouselDao)

}