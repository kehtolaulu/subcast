package com.kehtolaulu.subcast.presentation.feature.details.view

import com.arellomobile.mvp.MvpView
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.domain.feature.search.Podcast

interface DetailsView : MvpView {
    fun showPodcast(podcast: Podcast)
    fun updateAdapter(podcast: Podcast)
    fun submitListIntoAdapter(list: List<Episode>)
    fun addElementsToAdapter(list: List<Episode>)
    fun showError(error: Throwable)
}
