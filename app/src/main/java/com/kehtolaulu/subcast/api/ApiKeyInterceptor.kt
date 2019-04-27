package com.kehtolaulu.subcast.api

import com.kehtolaulu.subcast.constants.API_KEY_PARAM
import com.kehtolaulu.subcast.database.AppDatabase
import com.kehtolaulu.subcast.database.TokenDao
import com.kehtolaulu.subcast.services.TokenService
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor(private var service: TokenService) : Interceptor {
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
        return request.url().newBuilder()
            .addQueryParameter(API_KEY_PARAM, apiKey)
            .build()
    }

    private fun getToken(): String {
        return service.getToken().token.toString()
    }

    companion object {
        fun create(service: TokenService): Interceptor {
            return ApiKeyInterceptor(service)
        }
    }
}
