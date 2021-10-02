package com.toolbox.bestmovies.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(private val type: String?, private val accessToken: String?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(
            "Authorization", "$type" + " "+  "$accessToken").build()
        return chain.proceed(request)
    }
}