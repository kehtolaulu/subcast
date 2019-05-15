package com.kehtolaulu.subcast.services

import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.entities.LoginResponse
import com.kehtolaulu.subcast.entities.CredentialsBody
import io.reactivex.Single

class AuthService(private val subcastApi: SubcastApi){

    fun login(username: String, password: String): Single<LoginResponse> {
        return subcastApi.login(CredentialsBody(username, password))
    }

    fun register(username: String, password: String): Single<LoginResponse> {
        return subcastApi.register(CredentialsBody(username, password))
    }

}