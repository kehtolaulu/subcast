package com.kehtolaulu.subcast.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.kehtolaulu.subcast.entities.Podcast

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavouritesView : MvpView {
    fun updateAdapter()
    fun submitListIntoAdapter(list: List<Podcast>)
    fun addElementsToAdapter(list: List<Podcast>)
    fun showError(error: Throwable)
}
