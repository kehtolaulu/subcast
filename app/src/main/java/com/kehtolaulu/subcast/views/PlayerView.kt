package com.kehtolaulu.subcast.views

import com.arellomobile.mvp.MvpView
import com.kehtolaulu.subcast.entities.Episode

interface PlayerView : MvpView {
    fun showEpisode(episode: Episode)
}
