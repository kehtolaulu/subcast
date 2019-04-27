package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.di.scope.DownloadScope
import com.kehtolaulu.subcast.presenters.DownloadsPresenter
import com.kehtolaulu.subcast.services.DownloadsService
import dagger.Module
import dagger.Provides

@Module
class DownloadModule {

    @DownloadScope
    @Provides
    fun provideDownloadService(dao: EpisodeDao): DownloadsService = DownloadsService(dao)

    @DownloadScope
    @Provides
    fun providePresenter(service: DownloadsService): DownloadsPresenter = DownloadsPresenter(service)
}
