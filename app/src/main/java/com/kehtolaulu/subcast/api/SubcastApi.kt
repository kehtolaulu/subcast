package com.kehtolaulu.subcast.api

import com.kehtolaulu.subcast.entities.Podcast
import io.reactivex.Single
import retrofit2.http.GET

interface SubcastApi {
    @GET("subscriptions")
    fun getSubscriptions() : Single<List<Podcast>>
}