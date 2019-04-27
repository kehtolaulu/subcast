package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.SearchService
import com.kehtolaulu.subcast.views.SearchView

@InjectViewState
class SearchPresenter(private val service: SearchService) : MvpPresenter<SearchView>() {
    fun updateAdapter(query: String) {
        service
            .getPodcastsByName(query)
            .subscribe(
                {
                    it?.let(viewState::submitListIntoAdapter)
                },
                viewState::showError
            )
    }
}
