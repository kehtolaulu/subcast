package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.DownloadsService
import com.kehtolaulu.subcast.views.DownloadsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class DownloadsPresenter(private val service: DownloadsService) : MvpPresenter<DownloadsView>() {
    fun updateAdapter() {
        service.getDownloads()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ viewState.submitListIntoAdapter(it) }, viewState::showError)
    }
}
