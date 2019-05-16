package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.data.network.DownloadApi
import com.kehtolaulu.subcast.data.network.ItunesApi
import com.kehtolaulu.subcast.data.network.SubcastApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideDownloadApi(@Named("ITUNES_RETROFIT") retrofit: Retrofit): DownloadApi =
        retrofit.create(DownloadApi::class.java)

    @Provides
    @Singleton
    fun provideItunesApi(@Named("ITUNES_RETROFIT") retrofit: Retrofit): ItunesApi =
        retrofit.create(ItunesApi::class.java)

    @Provides
    @Singleton
    @Named("SUBCAST_API")
    fun provideSubcastApi(@Named("SUBCAST_RETROFIT") retrofit: Retrofit) : SubcastApi =
        retrofit.create(SubcastApi::class.java)
}
