package com.kehtolaulu.subcast.presentation.feature.playlater.view

import com.arellomobile.mvp.MvpView
import com.kehtolaulu.subcast.domain.feature.details.Episode

interface LaterView : MvpView {
    fun updateAdapter()
    fun submitListIntoAdapter(list: List<Episode>)
    fun addElementsToAdapter(list: List<Episode>)
    fun showError(error: Throwable)
}
