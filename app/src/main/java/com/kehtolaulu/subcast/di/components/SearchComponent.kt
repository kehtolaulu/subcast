package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.SearchModule
import com.kehtolaulu.subcast.di.scope.SearchScope
import com.kehtolaulu.subcast.presentation.feature.search.presenter.SearchPresenter
import com.kehtolaulu.subcast.presentation.feature.search.activity.SearchFragment
import dagger.Component

@SearchScope
@Component(dependencies = [AppComponent::class], modules = [SearchModule::class])
interface SearchComponent {

    fun inject(fragment: SearchFragment)

    fun providePresenter(): SearchPresenter
}
