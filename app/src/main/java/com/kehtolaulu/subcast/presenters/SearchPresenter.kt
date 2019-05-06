package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.services.EpisodesService
import com.kehtolaulu.subcast.services.PodcastsService
import com.kehtolaulu.subcast.views.SearchView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback

@InjectViewState
class SearchPresenter(private val service: PodcastsService) : MvpPresenter<SearchView>() {
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
