package com.kehtolaulu.subcast.domain.feature.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CredentialsBody (
    @SerializedName("username")
    @Expose
    var username: String? = null,
    @SerializedName("password")
    @Expose
    var password:String? = null
)
