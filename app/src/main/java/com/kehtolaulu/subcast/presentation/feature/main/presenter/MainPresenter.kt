package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.services.EpisodesService
import com.kehtolaulu.subcast.services.PodcastsService
import com.kehtolaulu.subcast.views.MainView

@InjectViewState
class MainPresenter(private val episodesService: EpisodesService, private val podcastsService: PodcastsService) :
    MvpPresenter<MainView>() {

    fun download(episode: Episode) {
        episodesService.download(episode)
    }

    fun listenLater(episode: Episode) {
        episodesService.listenLater(episode)
        viewState.showSuccess("added to listen later")
    }

    fun subscribe(podcast: Podcast) {
        podcastsService.subscribe(podcast)
        viewState.showSuccess("added to favourites")
    }
}