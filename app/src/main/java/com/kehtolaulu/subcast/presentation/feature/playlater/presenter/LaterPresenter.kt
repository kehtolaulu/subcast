package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.EpisodesService
import com.kehtolaulu.subcast.views.LaterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class LaterPresenter(private val episodesService: EpisodesService) : MvpPresenter<LaterView>() {
    private val compositeDisposable = CompositeDisposable()

    fun updateAdapter() {
        val disposable = episodesService.getListenLater()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ viewState.submitListIntoAdapter(it) }, viewState::showError)
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: LaterView?) {
        compositeDisposable.clear()
        super.destroyView(view)
    }
}