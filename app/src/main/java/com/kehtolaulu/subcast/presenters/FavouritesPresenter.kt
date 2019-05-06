package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.PodcastsService
import com.kehtolaulu.subcast.views.FavouritesView

@InjectViewState
class FavouritesPresenter(private val service: PodcastsService) : MvpPresenter<FavouritesView>() {
    fun updateAdapter() {
    }
}
