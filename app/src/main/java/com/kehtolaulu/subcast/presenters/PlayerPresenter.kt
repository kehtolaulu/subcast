package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.PlayerService
import com.kehtolaulu.subcast.views.PlayerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class PlayerPresenter(private val service: PlayerService) : MvpPresenter<PlayerView>() {
    fun getEpisode(id: Int) {
        service.getEpisode(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ viewState.showEpisode(it) }, viewState::showError)
    }
}
