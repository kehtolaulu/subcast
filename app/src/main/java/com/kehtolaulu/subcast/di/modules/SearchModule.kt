package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.di.scope.SearchScope
import com.kehtolaulu.subcast.presentation.feature.search.presenter.SearchPresenter
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

//    @SearchScope
//    @Provides
//    fun providePodcastsService(itunesApi: ItunesApi, dao: PodcastDao, subcastApi: SubcastApi): PodcastsInteractor =
//        PodcastsInteractor(itunesApi, dao, subcastApi)

    @SearchScope
    @Provides
    fun providePresenter(podcastsInteractor: PodcastsInteractor): SearchPresenter =
        SearchPresenter(podcastsInteractor)
}
