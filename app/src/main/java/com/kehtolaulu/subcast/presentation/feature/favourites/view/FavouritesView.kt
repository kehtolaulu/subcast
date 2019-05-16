package com.kehtolaulu.subcast.presentation.feature.favourites.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.kehtolaulu.subcast.domain.feature.search.Podcast

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavouritesView : MvpView {
    fun submitListIntoAdapter(list: List<Podcast>)
    fun addElementsToAdapter(list: List<Podcast>)
    fun showError(error: Throwable)
}
