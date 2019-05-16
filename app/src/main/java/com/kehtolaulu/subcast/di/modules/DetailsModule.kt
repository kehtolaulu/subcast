package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.di.scope.DetailsScope
import com.kehtolaulu.subcast.presentation.feature.details.presenter.DetailsPresenter
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import com.kehtolaulu.subcast.data.interactor.RssInteractor
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

//    @DetailsScope
//    @Provides
//    fun provideEpisodeService(dao: EpisodeDao, api: SubcastApi): EpisodesInteractor =
//        EpisodesInteractor(dao, api)
//
//    @DetailsScope
//    @Provides
//    fun providePodcastsService(itunesApi: ItunesApi, dao: PodcastDao, subcastApi: SubcastApi): PodcastsInteractor =
//        PodcastsInteractor(itunesApi, dao, subcastApi)

    @DetailsScope
    @Provides
    fun providePresenter(
        episodesInteractor: EpisodesInteractor,
        podcastsInteractor: PodcastsInteractor,
        rssInteractor: RssInteractor
    ): DetailsPresenter =
        DetailsPresenter(podcastsInteractor, rssInteractor)
}
