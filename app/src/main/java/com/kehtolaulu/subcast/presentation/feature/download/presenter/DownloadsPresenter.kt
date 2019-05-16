package com.kehtolaulu.subcast.presentation.feature.download.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.presentation.feature.download.view.DownloadsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class DownloadsPresenter(private val interactor: EpisodesInteractor) : MvpPresenter<DownloadsView>() {
    private val compositeDisposable = CompositeDisposable()

    fun updateAdapter() {
        val disposable = interactor.getDownloads()
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
