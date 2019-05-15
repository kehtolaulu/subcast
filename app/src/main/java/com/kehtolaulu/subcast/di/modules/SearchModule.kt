package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.api.ItunesApi
import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.database.PodcastDao
import com.kehtolaulu.subcast.di.scope.PlayerScope
import com.kehtolaulu.subcast.di.scope.SearchScope
import com.kehtolaulu.subcast.presenters.SearchPresenter
import com.kehtolaulu.subcast.services.EpisodesService
import com.kehtolaulu.subcast.services.PodcastsService
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @SearchScope
    @Provides
    fun providePodcastsService(itunesApi: ItunesApi, dao: PodcastDao, subcastApi: SubcastApi): PodcastsService =
        PodcastsService(itunesApi, dao, subcastApi)

    @SearchScope
    @Provides
    fun providePresenter(podcastsService: PodcastsService): SearchPresenter =
        SearchPresenter(podcastsService)
}
