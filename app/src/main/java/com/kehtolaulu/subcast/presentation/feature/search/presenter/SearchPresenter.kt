package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.PodcastsService
import com.kehtolaulu.subcast.views.SearchView
import io.reactivex.disposables.CompositeDisposable

@InjectViewState
class SearchPresenter(private val service: PodcastsService) : MvpPresenter<SearchView>() {
    private val compositeDisposable = CompositeDisposable()

    fun updateAdapter(query: String) {
        val disposable = service
            .getPodcastsByName(query)
            .subscribe(
                {
                    it?.let(viewState::submitListIntoAdapter)
                },
                viewState::showError
            )
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: SearchView?) {
        compositeDisposable.clear()
        super.destroyView(view)
    }
}
