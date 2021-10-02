package com.toolbox.bestmovies.di

import androidx.annotation.Nullable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.toolbox.bestmovies.data.remote.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(@Nullable @Named("AuthType") type: String?,
                               @Nullable @Named("AuthToken") token: String?): AuthInterceptor = AuthInterceptor(type, token)

    @Singleton
    @Provides
    fun provideOkhttpClient(authInterceptor: AuthInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl("https://echo-serv.tbxnet.com/v1/mobile/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}