package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.EpisodesService
import com.kehtolaulu.subcast.views.DownloadsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class DownloadsPresenter(private val service: EpisodesService) : MvpPresenter<DownloadsView>() {
    private val compositeDisposable = CompositeDisposable()

    fun updateAdapter() {
        val disposable = service.getDownloads()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ viewState.submitListIntoAdapter(it) }, viewState::showError)

        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: DownloadsView?) {
        compositeDisposable.clear()
        super.destroyView(view)
    }
}
