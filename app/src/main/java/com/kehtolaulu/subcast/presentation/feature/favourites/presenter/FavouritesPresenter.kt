package com.kehtolaulu.subcast.presentation.feature.favourites.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import com.kehtolaulu.subcast.presentation.feature.favourites.view.FavouritesView
import io.reactivex.disposables.CompositeDisposable

@InjectViewState
class FavouritesPresenter(private val interactor: PodcastsInteractor) : MvpPresenter<FavouritesView>() {
    private val compositeDisposable = CompositeDisposable()

    fun updateAdapter() {
        val disposable = interactor.getFavourites()
            .subscribe({ viewState.submitListIntoAdapter(it) }, viewState::showError)
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: FavouritesView?) {
        compositeDisposable.clear()
        super.destroyView(view)
    }
}
