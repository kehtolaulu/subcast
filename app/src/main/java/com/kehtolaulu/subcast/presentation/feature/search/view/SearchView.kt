package com.kehtolaulu.subcast.presentation.feature.search.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.kehtolaulu.subcast.domain.feature.search.Podcast

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchView : MvpView {
    fun updateAdapterByQueryResult(query: String)
    fun showError(error: Throwable)
    fun submitListIntoAdapter(list: List<Podcast>)
}
