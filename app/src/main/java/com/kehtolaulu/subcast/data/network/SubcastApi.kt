package com.kehtolaulu.subcast.data.network

import com.kehtolaulu.subcast.domain.feature.favourites.StatusResponse
import com.kehtolaulu.subcast.domain.feature.favourites.SubscriptionsRequest
import com.kehtolaulu.subcast.domain.feature.favourites.SubscriptionsResponse
import com.kehtolaulu.subcast.domain.feature.login.CredentialsBody
import com.kehtolaulu.subcast.domain.feature.login.LoginResponse
import com.kehtolaulu.subcast.domain.feature.playlater.ListenLaterRequest
import com.kehtolaulu.subcast.domain.feature.playlater.ListenLaterResponse
import com.kehtolaulu.subcast.domain.feature.progress.ProgressRequest
import com.kehtolaulu.subcast.domain.feature.progress.ProgressResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface SubcastApi {
    @POST("auth")
    fun login(@Body credentialsBody: CredentialsBody): Single<LoginResponse>

    @POST("register")
    fun register(@Body credentialsBody: CredentialsBody): Single<LoginResponse>

    //Progress

    @Headers("Content-Type: application/json")
    @GET("progress")
    fun getAllProgress(): Single<ProgressResponse>

    @POST("progress")
    fun saveProgress(@Body progressRequest: ProgressRequest): Single<StatusResponse>

    @Headers("Content-Type: application/json")
    @GET("progress")
    fun getProgress(@Body listenLaterRequest: ListenLaterRequest): Single<ProgressResponse>

    //Subscriptions
    @Headers("Content-Type: application/json")
    @GET("subscriptions")
    fun getSubscriptions(): Single<SubscriptionsResponse>

    @POST("subscriptions")
    fun subscribe(@Body subscriptionsRequest: SubscriptionsRequest): Single<StatusResponse>

    //Play Later

    @POST("play_later")
    fun addPlayLater(@Body listenLaterRequest: ListenLaterRequest): Single<StatusResponse>

    @Headers("Content-Type: application/json")
    @GET("play_later")
    fun getPlayLater(): Single<ListenLaterResponse>

}
