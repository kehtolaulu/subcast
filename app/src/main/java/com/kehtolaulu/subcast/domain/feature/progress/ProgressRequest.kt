package com.kehtolaulu.subcast.domain.feature.progress

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProgressRequest(
    @SerializedName("token")
    @Expose
    var token: String? = null,
    @SerializedName("guid")
    @Expose
    var guid: String? = null,
    @SerializedName("time")
    @Expose
    var time: Int? = null,
    @SerializedName("podcastId")
    @Expose
    var podcastId: Int? = null
)
