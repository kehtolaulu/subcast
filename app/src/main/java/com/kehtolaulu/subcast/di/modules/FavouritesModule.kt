package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.api.ItunesApi
import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.PodcastDao
import com.kehtolaulu.subcast.di.scope.FavouritesScope
import com.kehtolaulu.subcast.presenters.FavouritesPresenter
import com.kehtolaulu.subcast.services.PodcastsService
import dagger.Module
import dagger.Provides

@Module
class FavouritesModule {
    @FavouritesScope
    @Provides
    fun providePodcastsService(itunesApi: ItunesApi, podcastDao: PodcastDao, subcastApi: SubcastApi): PodcastsService =
        PodcastsService(itunesApi, podcastDao, subcastApi)

    @FavouritesScope
    @Provides
    fun providePresenter(service: PodcastsService): FavouritesPresenter = FavouritesPresenter(service)
}
