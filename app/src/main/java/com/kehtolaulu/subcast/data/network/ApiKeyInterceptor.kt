package com.kehtolaulu.subcast.data.network

import com.kehtolaulu.subcast.helpers.API_KEY_PARAM
import com.kehtolaulu.subcast.data.interactor.TokenInteractor
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor(private var interactor: TokenInteractor) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        return chain.proceed(
            original.newBuilder()
                .url(getUrl(original))
                .build()
        )
    }

    private fun getUrl(request: Request): HttpUrl {
        var apiKey = getToken()
        interactor.getToken()
        if (request.method() == "GET") {
            return request.url().newBuilder()
                .addQueryParameter(API_KEY_PARAM, apiKey)
                .build()
        }
        return request.url().newBuilder()
            .build()
    }

    private fun getToken(): String {
        return interactor.getToken().toString()
    }

    companion object {
        fun create(interactor: TokenInteractor): Interceptor {
            return ApiKeyInterceptor(interactor)
        }
    }
}
