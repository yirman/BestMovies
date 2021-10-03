package com.toolbox.bestmovies.di

import com.toolbox.bestmovies.data.local.CarouselDao
import com.toolbox.bestmovies.data.remote.CarouselRemoteDataSource
import com.toolbox.bestmovies.data.repository.CarouselRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object RepositoryModule {

    @FragmentScoped
    @Provides
    fun provideCarouselRepository(remoteDataSource: CarouselRemoteDataSource, carouselDao: CarouselDao) =
        CarouselRepository(remoteDataSource, carouselDao)

}