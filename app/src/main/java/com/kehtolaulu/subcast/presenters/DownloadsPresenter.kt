package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.DownloadsService
import com.kehtolaulu.subcast.views.DownloadsView

@InjectViewState
class DownloadsPresenter(private val service: DownloadsService) : MvpPresenter<DownloadsView>() {
    fun updateAdapter() {
        service.getDownloads()?.let { viewState.submitListIntoAdapter(it) }
    }
}
