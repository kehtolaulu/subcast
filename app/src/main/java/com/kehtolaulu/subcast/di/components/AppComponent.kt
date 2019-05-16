package com.kehtolaulu.subcast.di.components

import android.content.Context
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.data.network.DownloadApi
import com.kehtolaulu.subcast.data.network.ItunesApi
import com.kehtolaulu.subcast.data.database.EpisodeDao
import com.kehtolaulu.subcast.data.database.PodcastDao
import com.kehtolaulu.subcast.data.database.TokenDao
import com.kehtolaulu.subcast.data.interactor.*
import com.kehtolaulu.subcast.presentation.service.PlayerServiceConnection
import com.kehtolaulu.subcast.di.modules.ApiModule
import com.kehtolaulu.subcast.di.modules.AppModule
import com.kehtolaulu.subcast.di.modules.NetModule
import com.kehtolaulu.subcast.di.modules.ServiceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class, ApiModule::class, ServiceModule::class])
interface AppComponent {

    fun inject(app: MyApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyApplication): Builder

        @BindsInstance
        fun context(@Named("context") context: Context): Builder

        fun build(): AppComponent
    }

    //    delete
    fun provideContext(): Context

    fun provideRssDealer(): RssInteractor

    fun provideEpisodeDao(): EpisodeDao

    fun providePodcastDao(): PodcastDao

    fun provideTokenDao(): TokenDao

    fun provideDeleter(): DatabaseInteractor

    fun provideItunesApi(): ItunesApi

//    fun provideSubcastApi(): SubcastApi

    fun provideDownloadApi(): DownloadApi

    fun providePodcastsService(): PodcastsInteractor

    fun provideTokenService(): TokenInteractor

    fun provideAuthService(): AuthInteractor

    fun provideEpisodesService(): EpisodesInteractor

    fun provideApp(): MyApplication

    fun provideSyncService(): SyncInteractor

    fun injectServiceConnection(serviceConnection: PlayerServiceConnection)
}
