package com.kehtolaulu.subcast.data.interactor

import com.kehtolaulu.subcast.data.network.SubcastApi
import com.kehtolaulu.subcast.domain.feature.login.LoginResponse
import com.kehtolaulu.subcast.domain.feature.login.CredentialsBody
import io.reactivex.Single

class AuthInteractor(private val subcastApi: SubcastApi){

    fun login(username: String, password: String): Single<LoginResponse> {
        return subcastApi.login(CredentialsBody(username, password))
    }

    fun register(username: String, password: String): Single<LoginResponse> {
        return subcastApi.register(CredentialsBody(username, password))
    }

}