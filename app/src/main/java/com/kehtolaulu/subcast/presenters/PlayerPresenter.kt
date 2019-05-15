package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.services.EpisodesService
import com.kehtolaulu.subcast.views.PlayerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class PlayerPresenter(private val service: EpisodesService) : MvpPresenter<PlayerView>() {
    fun showEpisode(episode: Episode) {
         viewState.showEpisode(episode)
    }


}
