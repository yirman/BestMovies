package com.toolbox.bestmovies.data.remote

class AuthRemoteDataSource constructor(
    private val authService: AuthService
): RemoteDataSource(){

    suspend fun requestAuth(key: String) = getResult { authService.requestAuth(key) }

}