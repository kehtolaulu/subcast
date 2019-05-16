package com.kehtolaulu.subcast.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubscriptionsResponse (
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("subscriptions")
    @Expose
    var subscriptions: List<Podcast>? = null
)
