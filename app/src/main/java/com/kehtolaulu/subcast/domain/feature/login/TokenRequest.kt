package com.kehtolaulu.subcast.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("token")
    @Expose
    var token: String? = null
)
