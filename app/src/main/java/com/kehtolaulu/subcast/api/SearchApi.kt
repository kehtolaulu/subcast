package com.kehtolaulu.subcast.api

import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.entities.PodcastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search")
    fun search(@Query("entity") entity: String, @Query("term") term: String) : Single<PodcastResponse>
}
