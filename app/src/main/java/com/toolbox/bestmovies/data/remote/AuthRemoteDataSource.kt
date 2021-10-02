package com.toolbox.bestmovies.data.remote

import dagger.Lazy
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authService: Lazy<AuthService>
): RemoteDataSource(){

    suspend fun requestAuth(key: String) = getResult { authService.get().requestAuth(key) }

}