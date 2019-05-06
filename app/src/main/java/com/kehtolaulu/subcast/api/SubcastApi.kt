package com.kehtolaulu.subcast.api

import com.kehtolaulu.subcast.entities.LoginResponse
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.entities.CredentialsBody
import io.reactivex.Single
import retrofit2.http.*

interface SubcastApi {
    @GET("subscriptions")
    fun getSubscriptions(): Single<List<Podcast>>

    @POST("auth")
    fun login(@Body credentialsBody: CredentialsBody): Single<LoginResponse>

    @POST("register")
    fun register(@Body credentialsBody: CredentialsBody): Single<LoginResponse>
}
