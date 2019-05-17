package com.kehtolaulu.subcast.domain.feature.playlater

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListenLaterRequest(
    @SerializedName("token")
    @Expose
    var token: String? = null,
    @SerializedName("guid")
    @Expose
    var guid: String? = null,
    @SerializedName("podcastId")
    @Expose
    var podcastId: Int? = null,
    @SerializedName("link")
    @Expose
    var url: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null
)
