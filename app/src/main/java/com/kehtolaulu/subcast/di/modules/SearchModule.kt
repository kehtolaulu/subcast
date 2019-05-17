package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import com.kehtolaulu.subcast.di.scope.SearchScope
import com.kehtolaulu.subcast.presentation.feature.search.presenter.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @SearchScope
    @Provides
    fun providePresenter(podcastsInteractor: PodcastsInteractor): SearchPresenter =
        SearchPresenter(podcastsInteractor)
}
