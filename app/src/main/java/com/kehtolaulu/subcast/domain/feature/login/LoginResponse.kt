package com.kehtolaulu.subcast.domain.feature.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("token")
    @Expose
    var token: String? = null,
    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String? = null
)
