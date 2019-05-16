package com.kehtolaulu.subcast.domain.feature.favourites

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubscriptionsRequest (
    @SerializedName("token")
    @Expose
    var token: String? = null,
    @SerializedName("podcastId")
    @Expose
    var podcastId: Int? = null
)
