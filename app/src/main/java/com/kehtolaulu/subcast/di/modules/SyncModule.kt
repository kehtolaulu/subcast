package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.data.interactor.*
import com.kehtolaulu.subcast.di.scope.SyncScope
import com.kehtolaulu.subcast.presentation.feature.sync.presenter.SyncPresenter
import dagger.Module
import dagger.Provides

@Module
class SyncModule {
    @SyncScope
    @Provides
    fun provideSyncPresenter(
        tokenInteractor: TokenInteractor,
        syncInteractor: SyncInteractor,
        podcastsInteractor: PodcastsInteractor,
        episodesInteractor: EpisodesInteractor,
        interactor: DatabaseInteractor,
        rssInteractor: RssInteractor
    ): SyncPresenter =
        SyncPresenter(
            tokenInteractor,
            syncInteractor,
            podcastsInteractor,
            episodesInteractor,
            interactor,
            rssInteractor
        )
}
