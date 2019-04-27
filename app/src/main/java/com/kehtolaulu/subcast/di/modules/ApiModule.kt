package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.api.DownloadApi
import com.kehtolaulu.subcast.api.SearchApi
import com.kehtolaulu.subcast.api.SubcastApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideDownloadApi(@Named("SUBCAST_RETROFIT") retrofit: Retrofit): DownloadApi =
        retrofit.create(DownloadApi::class.java)

    @Provides
    @Singleton
    fun provideSearchApi(@Named("ITUNES_RETROFIT") retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)

    @Provides
    @Singleton
    fun provideSubcastApi(@Named("SUBCAST_RETROFIT") retrofit: Retrofit) : SubcastApi =
        retrofit.create(SubcastApi::class.java)
}
