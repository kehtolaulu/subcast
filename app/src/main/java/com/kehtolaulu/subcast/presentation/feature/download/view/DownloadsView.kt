package com.kehtolaulu.subcast.presentation.feature.download.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.kehtolaulu.subcast.domain.feature.details.Episode

@StateStrategyType(AddToEndSingleStrategy::class)
interface DownloadsView : MvpView {
    fun updateAdapter()
    fun submitListIntoAdapter(list: List<Episode>)
    fun addElementsToAdapter(list: List<Episode>)
    fun showError(error: Throwable)
}
