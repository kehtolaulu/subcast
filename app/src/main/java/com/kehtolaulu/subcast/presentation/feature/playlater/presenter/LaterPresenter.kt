package com.kehtolaulu.subcast.presentation.feature.playlater.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.presentation.feature.playlater.view.LaterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class LaterPresenter(private val episodesInteractor: EpisodesInteractor) : MvpPresenter<LaterView>() {
    private val compositeDisposable = CompositeDisposable()

    fun updateAdapter() {
        val disposable = episodesInteractor.getListenLater()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.submitListIntoAdapter(it.filter { episode -> episode.url != "" })
                },
                viewState::showError
            )
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: LaterView?) {
        compositeDisposable.clear()
        super.destroyView(view)
    }
}
