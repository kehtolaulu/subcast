package com.kehtolaulu.subcast.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface DownloadApi {
    @GET
    fun downloadFileByUrl(@Url url: String): Call<ResponseBody>
}
