package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import com.kehtolaulu.subcast.data.interactor.RssInteractor
import com.kehtolaulu.subcast.di.scope.DetailsScope
import com.kehtolaulu.subcast.presentation.feature.details.presenter.DetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

    @DetailsScope
    @Provides
    fun providePresenter(
        episodesInteractor: EpisodesInteractor,
        podcastsInteractor: PodcastsInteractor,
        rssInteractor: RssInteractor
    ): DetailsPresenter =
        DetailsPresenter(podcastsInteractor, rssInteractor)
}
