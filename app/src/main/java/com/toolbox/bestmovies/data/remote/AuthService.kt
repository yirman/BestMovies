package com.toolbox.bestmovies.data.remote

import com.toolbox.bestmovies.data.entities.Auth
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("auth")
    fun requestAuth(@Field("sub") key: String): Call<Auth>

}