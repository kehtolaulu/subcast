package com.kehtolaulu.subcast.views

import com.arellomobile.mvp.MvpView
import com.kehtolaulu.subcast.entities.Episode

interface LaterView : MvpView {
    fun updateAdapter()
    fun submitListIntoAdapter(list: List<Episode>)
    fun addElementsToAdapter(list: List<Episode>)
    fun showError(error: Throwable)
}