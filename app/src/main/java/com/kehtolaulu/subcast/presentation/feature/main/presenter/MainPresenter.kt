package com.kehtolaulu.subcast.presentation.feature.main.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.domain.feature.search.Podcast
import com.kehtolaulu.subcast.presentation.feature.main.view.MainView

@InjectViewState
class MainPresenter(private val episodesInteractor: EpisodesInteractor, private val podcastsInteractor: PodcastsInteractor) :
    MvpPresenter<MainView>() {

    fun download(episode: Episode) {
        episodesInteractor.download(episode)
    }

    fun listenLater(episode: Episode) {
        episodesInteractor.listenLater(episode)
        viewState.showSuccess("added to listen later")
    }

    fun subscribe(podcast: Podcast) {
        podcastsInteractor.subscribe(podcast)
        viewState.showSuccess("added to favourites")
    }
}
