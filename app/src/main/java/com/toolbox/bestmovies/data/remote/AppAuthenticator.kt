package com.toolbox.bestmovies.data.remote

import com.toolbox.bestmovies.data.local.AuthDao
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject


class AppAuthenticator @Inject constructor(val sub: String, val authRemoteDataSource: Lazy<AuthRemoteDataSource>, val authDao: AuthDao): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val result = authRemoteDataSource.get().requestAuth(sub)
            if(result.status == Resource.Status.SUCCESS)
                authDao.insertAuth(result.data!!)
                response.request.newBuilder()
                    .header(
                        "Authorization",
                        result.data?.type + " " + result.data?.token
                    )
                    .build()
            null
        }
    }
}