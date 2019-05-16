package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.di.scope.MainScope
import com.kehtolaulu.subcast.presentation.feature.main.presenter.MainPresenter
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @MainScope
    @Provides
    fun providePresenter(episodesInteractor: EpisodesInteractor, podcastsInteractor: PodcastsInteractor): MainPresenter =
        MainPresenter(episodesInteractor, podcastsInteractor)
}
