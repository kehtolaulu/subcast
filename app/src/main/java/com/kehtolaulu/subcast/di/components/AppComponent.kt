package com.kehtolaulu.subcast.di.components

import android.content.Context
import com.kehtolaulu.subcast.api.SearchApi
import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.di.modules.ApiModule
import com.kehtolaulu.subcast.di.modules.AppModule
import com.kehtolaulu.subcast.di.modules.NetModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class, ApiModule::class])
interface AppComponent {
//    delete
    fun provideContext(): Context

    fun provideEpisodeDao(): EpisodeDao

    fun provideSearchApi(): SearchApi

    fun provideSubcastApi(): SubcastApi
}
