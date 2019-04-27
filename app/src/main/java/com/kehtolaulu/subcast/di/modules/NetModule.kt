package com.kehtolaulu.subcast.di.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.kehtolaulu.subcast.api.ApiKeyInterceptor
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.database.TokenDao
import com.kehtolaulu.subcast.di.scope.PlayerScope
import com.kehtolaulu.subcast.services.PlayerService
import com.kehtolaulu.subcast.services.TokenService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {
    @Provides
    fun provideTokenService(dao: TokenDao): TokenService = TokenService(dao)

    @Provides
    @Singleton
    @Named(NAME_API_KEY)
    fun provideApiKeyInterceptor(service: TokenService): Interceptor = ApiKeyInterceptor.create(service)

    @Provides
    @Singleton
    @Named(NAME_STETHO)
    fun provideStethoInterceptor(): Interceptor = StethoInterceptor()

    @Provides
    @Singleton
    @Named(SUBCAST_RETROFIT)
    fun provideSubcastOkHttpClient(
        @Named(NAME_STETHO) stethoInterceptor: Interceptor,
        @Named(NAME_API_KEY) apiKeyInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(stethoInterceptor)
            .build()

    @Provides
    @Singleton
    @Named(ITUNES_RETROFIT)
    fun provideItunesOkHttpClient(
        @Named(NAME_STETHO) stethoInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(stethoInterceptor)
            .build()

    @Provides
    @Singleton
    @Named(ITUNES_URL)
    fun provideItunesUrlString(): String = "https://itunes.apple.com/"

    @Provides
    @Singleton
    @Named(SUBCAST_URL)
    fun provideSubcastUrlString(): String = "http://subcast.herokuapp.com/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    @Named(ITUNES_RETROFIT)
    fun provideItunesRetrofit(
        @Named(ITUNES_RETROFIT) client: OkHttpClient,
        converterFactory: GsonConverterFactory,
        callAdapterFactory: RxJava2CallAdapterFactory,
        @Named(ITUNES_URL) baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()

    @Provides
    @Singleton
    @Named("SUBCAST_RETROFIT")
    fun provideSubcastRetrofit(
        @Named(SUBCAST_RETROFIT) client: OkHttpClient,
        converterFactory: GsonConverterFactory,
        callAdapterFactory: RxJava2CallAdapterFactory,
        @Named(SUBCAST_URL) baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()

    companion object {
        private const val ITUNES_RETROFIT = "ITUNES_RETROFIT"
        private const val SUBCAST_RETROFIT = "SUBCAST_RETROFIT"
        private const val NAME_STETHO = "StethoInterceptor"
        private const val ITUNES_URL = "ItunesURL"
        private const val SUBCAST_URL = "SubcastURL"
        private const val NAME_API_KEY = "ApiKey"
    }
}
