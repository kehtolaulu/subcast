package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.di.scope.DownloadScope
import com.kehtolaulu.subcast.presentation.feature.download.presenter.DownloadsPresenter
import dagger.Module
import dagger.Provides

@Module
class DownloadModule {

    @DownloadScope
    @Provides
    fun providePresenter(interactor: EpisodesInteractor): DownloadsPresenter =
        DownloadsPresenter(interactor)
}
