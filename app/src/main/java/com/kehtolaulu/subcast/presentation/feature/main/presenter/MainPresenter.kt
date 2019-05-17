package com.kehtolaulu.subcast.presentation.feature.main.presenter

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.domain.feature.search.Podcast
import com.kehtolaulu.subcast.presentation.feature.main.view.MainView

@InjectViewState
class MainPresenter(
    private val episodesInteractor: EpisodesInteractor,
    private val podcastsInteractor: PodcastsInteractor,
    private val context: Context
) :
    MvpPresenter<MainView>() {

    fun download(episode: Episode) {
        episodesInteractor.download(episode)
    }

    fun listenLater(episode: Episode) {
        episodesInteractor.listenLater(episode)
        viewState.showSuccess(context.getString(R.string.later_success))
    }

    fun subscribe(podcast: Podcast) {
        podcastsInteractor.subscribe(podcast)
        viewState.showSuccess(context.getString(R.string.fav_success))
    }
}
