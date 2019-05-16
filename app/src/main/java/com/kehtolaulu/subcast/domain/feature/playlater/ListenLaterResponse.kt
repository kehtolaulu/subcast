package com.kehtolaulu.subcast.domain.feature.playlater

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kehtolaulu.subcast.domain.feature.details.Episode

data class ListenLaterResponse(
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("playLater")
    var list: List<Episode>? = null
)