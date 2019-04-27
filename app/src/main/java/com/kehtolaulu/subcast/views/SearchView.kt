package com.kehtolaulu.subcast.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.kehtolaulu.subcast.entities.Podcast

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchView : MvpView {
    fun updateAdapterByQueryResult(query: String)
    fun showError(error: Throwable)
    fun submitListIntoAdapter(list: List<Podcast>)
}
