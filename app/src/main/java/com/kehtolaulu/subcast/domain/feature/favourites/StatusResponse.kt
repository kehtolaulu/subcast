package com.kehtolaulu.subcast.domain.feature.favourites

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StatusResponse(
    @SerializedName("status")
    @Expose
    var status: String? = null
)
