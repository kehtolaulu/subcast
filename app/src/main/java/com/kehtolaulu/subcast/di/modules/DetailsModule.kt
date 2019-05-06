package com.kehtolaulu.subcast.di.modules

import android.content.Context
import com.kehtolaulu.subcast.api.ItunesApi
import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.database.PodcastDao
import com.kehtolaulu.subcast.di.scope.DetailsScope
import com.kehtolaulu.subcast.di.scope.SearchScope
import com.kehtolaulu.subcast.presenters.DetailsPresenter
import com.kehtolaulu.subcast.services.EpisodesService
import com.kehtolaulu.subcast.services.PodcastsService
import com.kehtolaulu.subcast.services.RssDealer
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

    @DetailsScope
    @Provides
    fun provideEpisodeService(dao: EpisodeDao, api: SubcastApi): EpisodesService =
        EpisodesService(dao, api)

    @DetailsScope
    @Provides
    fun providePodcastsService(itunesApi: ItunesApi, dao: PodcastDao, subcastApi: SubcastApi): PodcastsService =
        PodcastsService(itunesApi, dao, subcastApi)

    @DetailsScope
    @Provides
    fun providePresenter(
        episodesService: EpisodesService,
        podcastsService: PodcastsService,
        rssDealer: RssDealer
    ): DetailsPresenter =
        DetailsPresenter(episodesService, podcastsService, rssDealer)
}
