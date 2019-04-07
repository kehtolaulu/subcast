package com.kehtolaulu.subcast.retrofit

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit private constructor(){
    companion object {
        val instance : Retrofit by lazy {
            Holder.INSTANCE
        }
    }

    private object Holder{
        val INSTANCE = Retrofit()
    }

    fun getDownloadService() : DownloadApi{
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())

        okHttpClient.interceptors().add(Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder()
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        })

        val retrofit = retrofit2.Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://feeds.soundcloud.com")
            .client(okHttpClient.build())
            .build()

        return retrofit.create(DownloadApi::class.java)
    }

}
