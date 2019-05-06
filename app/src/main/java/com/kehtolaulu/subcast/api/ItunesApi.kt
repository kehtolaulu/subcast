package com.kehtolaulu.subcast.api

import com.kehtolaulu.subcast.entities.PodcastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {
    @GET("search")
    fun searchByName(@Query("entity") entity: String, @Query("term") term: String): Single<PodcastResponse>

    @GET("lookup")
    fun searchById(@Query("entity") entity: String, @Query("id") id: Int): Single<PodcastResponse>
}
