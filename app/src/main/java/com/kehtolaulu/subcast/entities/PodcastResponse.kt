package com.kehtolaulu.subcast.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PodcastResponse(
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int? = 0,
    @SerializedName("results")
    @Expose
    var results: List<Podcast>? = null
)
