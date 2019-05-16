package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.di.scope.DownloadScope
import com.kehtolaulu.subcast.presentation.feature.download.presenter.DownloadsPresenter
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import dagger.Module
import dagger.Provides

@Module
class DownloadModule {
//
//    @DownloadScope
//    @Provides
//    fun provideEpisodeService(dao: EpisodeDao, api: SubcastApi): EpisodesInteractor = EpisodesInteractor(dao, api)

    @DownloadScope
    @Provides
    fun providePresenter(interactor: EpisodesInteractor): DownloadsPresenter =
        DownloadsPresenter(interactor)
}
