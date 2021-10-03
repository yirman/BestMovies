package com.toolbox.bestmovies.data.remote

import com.toolbox.bestmovies.data.local.AuthDao
import dagger.Lazy
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject


class AppAuthenticator @Inject constructor(val sub: String, val authRemoteDataSource: Lazy<AuthRemoteDataSource>, val authDao: AuthDao): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {

        val result = authRemoteDataSource.get().requestAuth(sub).execute()
        if(result.isSuccessful && result.body() != null) {
            authDao.deleteAuth();
            authDao.insertAuth(result.body()!!)
            return response.request.newBuilder()
                .header(
                    "Authorization",
                    result.body()?.type + " " + result.body()?.token
                )
                .build()
        }
        return null
    }
}