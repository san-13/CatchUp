package com.sv.catchup.remote

import com.sv.catchup.data.*
import retrofit2.Call
import retrofit2.http.*

interface CatchUpApi {

    @GET("createNew")
    fun createChannel(): Call<channelCredential>

    @GET("verify/{channelName}")
    fun verifyChannel(@Path("channelName") channelName:String):Call<channelToken>

    @POST("users/register")
    fun signup(@Body sgnup:signupReqBody): Call<token>

    @POST("users/login")
    fun login(@Body sgnin:loginReqBody): Call<token>

    @POST("logout")
    fun logout(@Header("Authorization") token:String):Call<logout>

}