package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.PodcastsService
import com.kehtolaulu.subcast.views.FavouritesView
import io.reactivex.disposables.CompositeDisposable

@InjectViewState
class FavouritesPresenter(private val service: PodcastsService) : MvpPresenter<FavouritesView>() {
    private val compositeDisposable = CompositeDisposable()

    fun updateAdapter() {
        val disposable = service.getFavourites()
            .subscribe({ viewState.submitListIntoAdapter(it) }, viewState::showError)
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: FavouritesView?) {
        compositeDisposable.clear()
        super.destroyView(view)
    }
}
