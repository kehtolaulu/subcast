package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.PlayerService
import com.kehtolaulu.subcast.views.PlayerView

@InjectViewState
class PlayerPresenter(private val service: PlayerService) : MvpPresenter<PlayerView>() {
    fun getEpisode(id: Int) {
        service.getEpisode(id)?.let {
            viewState.showEpisode(it)
        }
    }
}
