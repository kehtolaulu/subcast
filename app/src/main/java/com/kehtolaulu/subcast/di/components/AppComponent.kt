package com.kehtolaulu.subcast.di.components

import android.content.Context
import com.kehtolaulu.subcast.api.ItunesApi
import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.database.PodcastDao
import com.kehtolaulu.subcast.database.TokenDao
import com.kehtolaulu.subcast.di.modules.ApiModule
import com.kehtolaulu.subcast.di.modules.AppModule
import com.kehtolaulu.subcast.di.modules.NetModule
import com.kehtolaulu.subcast.services.PlayerServiceConnection
import com.kehtolaulu.subcast.services.RssDealer
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class, ApiModule::class])
interface AppComponent {
//    delete
    fun provideContext(): Context

    fun provideRssDealer(): RssDealer

    fun provideEpisodeDao(): EpisodeDao

    fun providePodcastDao(): PodcastDao

    fun provideTokenDao(): TokenDao

    fun provideItunesApi(): ItunesApi

    fun provideSubcastApi(): SubcastApi

    fun injectServiceConnection(serviceConnectionObject: PlayerServiceConnection)

}
