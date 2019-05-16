package com.kehtolaulu.subcast.presentation.feature.player.view

import com.arellomobile.mvp.MvpView
import com.kehtolaulu.subcast.domain.feature.details.Episode

interface PlayerView : MvpView {
    fun showEpisode(episode: Episode)
    fun showError(error: Throwable)
    fun updatePosition(currentPosition: Int)
    fun updatePlayingEpisode(episode: Episode?)
}
