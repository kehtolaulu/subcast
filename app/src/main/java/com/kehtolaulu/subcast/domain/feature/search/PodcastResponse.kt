package com.kehtolaulu.subcast.domain.feature.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kehtolaulu.subcast.domain.feature.search.Podcast

data class PodcastResponse(
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int? = 0,
    @SerializedName("results")
    @Expose
    var results: List<Podcast>? = null
)
