package com.kehtolaulu.subcast.domain.feature.favourites

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kehtolaulu.subcast.domain.feature.search.Podcast

data class SubscriptionsResponse (
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("subscriptions")
    @Expose
    var subscriptions: List<Podcast>? = null
)
