package com.toolbox.bestmovies.di

import android.content.Context
import com.toolbox.bestmovies.data.local.AppDatabase
import com.toolbox.bestmovies.data.local.AuthDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCarouselDao(db: AppDatabase) = db.carouselDao()

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideAuthDao(db: AppDatabase) = db.authDao()

    @Singleton
    @Provides
    @Named("AuthType")
    fun provideAuthType(authDao: AuthDao) = authDao.queryAuth()?.type

    @Singleton
    @Provides
    @Named("AuthToken")
    fun provideAuthToken(authDao: AuthDao) = authDao.queryAuth()?.token
}