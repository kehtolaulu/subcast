package com.kehtolaulu.subcast.di.modules

import android.content.Context
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import com.kehtolaulu.subcast.di.scope.MainScope
import com.kehtolaulu.subcast.presentation.feature.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @MainScope
    @Provides
    fun providePresenter(
        episodesInteractor: EpisodesInteractor,
        podcastsInteractor: PodcastsInteractor,
        context: Context
    ): MainPresenter =
        MainPresenter(episodesInteractor, podcastsInteractor, context)
}
