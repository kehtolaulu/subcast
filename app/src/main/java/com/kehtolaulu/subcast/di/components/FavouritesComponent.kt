package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.FavouritesModule
import com.kehtolaulu.subcast.di.scope.FavouritesScope
import com.kehtolaulu.subcast.presentation.feature.favourites.presenter.FavouritesPresenter
import com.kehtolaulu.subcast.presentation.feature.favourites.activity.FavouritesFragment
import dagger.Component

@FavouritesScope
@Component(dependencies = [AppComponent::class], modules = [FavouritesModule::class])
interface FavouritesComponent {
    fun inject(fragment: FavouritesFragment)

    fun providePresenter(): FavouritesPresenter
}