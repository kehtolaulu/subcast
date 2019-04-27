package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.FavouritesService

@InjectViewState
class FavouritesPresenter(private val service: FavouritesService) : MvpPresenter<FavouritesView>() {
    fun updateAdapter() {
    }
}