package com.kehtolaulu.subcast.domain.feature.progress

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kehtolaulu.subcast.domain.feature.details.Episode

data class ProgressResponse(
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("progress")
    @Expose
    var progress: List<Episode>? = null
)