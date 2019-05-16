package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.di.scope.FavouritesScope
import com.kehtolaulu.subcast.presentation.feature.favourites.presenter.FavouritesPresenter
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import dagger.Module
import dagger.Provides

@Module
class FavouritesModule {
//    @FavouritesScope
//    @Provides
//    fun providePodcastsService(itunesApi: ItunesApi, podcastDao: PodcastDao, subcastApi: SubcastApi): PodcastsInteractor =
//        PodcastsInteractor(itunesApi, podcastDao, subcastApi)

    @FavouritesScope
    @Provides
    fun providePresenter(interactor: PodcastsInteractor): FavouritesPresenter =
        FavouritesPresenter(interactor)
}
