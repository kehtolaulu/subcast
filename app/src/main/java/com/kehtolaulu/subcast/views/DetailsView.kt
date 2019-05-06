package com.kehtolaulu.subcast.views

import com.arellomobile.mvp.MvpView
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.entities.Podcast

interface DetailsView : MvpView {
    fun showPodcast(podcast: Podcast)
    fun updateAdapter(podcast: Podcast)
    fun submitListIntoAdapter(list: List<Episode>)
    fun addElementsToAdapter(list: List<Episode>)
    fun showError(error: Throwable)
}
