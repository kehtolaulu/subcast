package com.kehtolaulu.subcast.di.modules

import android.content.Context
import com.kehtolaulu.subcast.data.network.DownloadApi
import com.kehtolaulu.subcast.data.network.ItunesApi
import com.kehtolaulu.subcast.data.network.SubcastApi
import com.kehtolaulu.subcast.data.database.EpisodeDao
import com.kehtolaulu.subcast.data.database.PodcastDao
import com.kehtolaulu.subcast.data.database.TokenDao
import com.kehtolaulu.subcast.data.interactor.*
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ApiModule::class, NetModule::class])
class ServiceModule {

    @Provides
    @Singleton
    fun providePodcastsService(
        itunesApi: ItunesApi,
        dao: PodcastDao,
        @Named("SUBCAST_API") subcastApi: SubcastApi,
        tokenInteractor: TokenInteractor
    ): PodcastsInteractor =
        PodcastsInteractor(itunesApi, dao, subcastApi, tokenInteractor)

    @Provides
    @Singleton
    fun provideTokenService(tokenDao: TokenDao, context: Context): TokenInteractor =
        TokenInteractor(tokenDao, context)

    @Provides
    @Singleton
    fun provideAuthService(@Named("SUBCAST_API") subcastApi: SubcastApi): AuthInteractor =
        AuthInteractor(subcastApi)

    @Provides
    @Singleton
    fun provideEpisodesService(
        dao: EpisodeDao,
        @Named("SUBCAST_API") subcastApi: SubcastApi,
        downloader: DownloadingInteractor,
        tokenInteractor: TokenInteractor
    ): EpisodesInteractor =
        EpisodesInteractor(dao, subcastApi, downloader, tokenInteractor)

    @Provides
    @Singleton
    fun provideDownloadingInteractor(api: DownloadApi, context: Context): DownloadingInteractor =
        DownloadingInteractor(api, context)

    @Provides
    @Singleton
    fun provideSyncService(
        @Named("SUBCAST_API") subcastApi: SubcastApi, episodeDao: EpisodeDao,
        podcastDao: PodcastDao
    ): SyncInteractor =
        SyncInteractor(subcastApi, episodeDao, podcastDao)

}
