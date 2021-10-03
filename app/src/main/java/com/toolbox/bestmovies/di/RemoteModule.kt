package com.toolbox.bestmovies.di

import androidx.annotation.Nullable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.toolbox.bestmovies.data.local.AuthDao
import com.toolbox.bestmovies.data.remote.*
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideAuthInterceptor(@Nullable @Named("AuthType") type: String?,
                               @Nullable @Named("AuthToken") token: String?): AuthInterceptor = AuthInterceptor(type, token)

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    @Named("Sub")
    fun provideSub(): String = "ToolboxMobileTest"

    @Singleton
    @Provides
    fun provideAppAuthenticator(@Named("Sub") sub: String, authRemoteDataSource: Lazy<AuthRemoteDataSource>, authDao: AuthDao) = AppAuthenticator(sub, authRemoteDataSource, authDao)

    @Singleton
    @Provides
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor, authInterceptor: AuthInterceptor, appAuthenticator: AppAuthenticator) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(appAuthenticator)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl("https://echo-serv.tbxnet.com/v1/mobile/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(authService: Lazy<AuthService>): AuthRemoteDataSource = AuthRemoteDataSource(authService)

    @Provides
    fun provideCarouselService(retrofit: Retrofit): CarouselService = retrofit.create(CarouselService::class.java)

    @Singleton
    @Provides
    fun provideCarouselRemoteDataSource(carouselService: Lazy<CarouselService>): CarouselRemoteDataSource = CarouselRemoteDataSource(carouselService)
}