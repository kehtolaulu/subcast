package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.di.scope.DownloadScope
import com.kehtolaulu.subcast.presenters.DownloadsPresenter
import com.kehtolaulu.subcast.services.EpisodesService
import dagger.Module
import dagger.Provides

@Module
class DownloadModule {

    @DownloadScope
    @Provides
    fun provideEpisodeService(dao: EpisodeDao, api: SubcastApi): EpisodesService = EpisodesService(dao, api)

    @DownloadScope
    @Provides
    fun providePresenter(service: EpisodesService): DownloadsPresenter = DownloadsPresenter(service)
}
