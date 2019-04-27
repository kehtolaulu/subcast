package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.ApiModule
import com.kehtolaulu.subcast.di.modules.SearchModule
import com.kehtolaulu.subcast.di.scope.SearchScope
import com.kehtolaulu.subcast.presenters.SearchPresenter
import com.kehtolaulu.subcast.ui.SearchFragment
import dagger.Component

@SearchScope
@Component(dependencies = [AppComponent::class], modules = [SearchModule::class])
interface SearchComponent {

    fun inject(fragment: SearchFragment)

    fun providePresenter(): SearchPresenter
}
