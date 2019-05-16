package com.kehtolaulu.subcast.presentation.feature.search.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import com.kehtolaulu.subcast.presentation.feature.search.view.SearchView
import io.reactivex.disposables.CompositeDisposable

@InjectViewState
class SearchPresenter(private val interactor: PodcastsInteractor) : MvpPresenter<SearchView>() {
    private val compositeDisposable = CompositeDisposable()

    fun updateAdapter(query: String) {
        val disposable = interactor
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
