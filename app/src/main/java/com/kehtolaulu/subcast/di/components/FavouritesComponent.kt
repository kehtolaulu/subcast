package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.DownloadModule
import com.kehtolaulu.subcast.di.modules.FavouritesModule
import com.kehtolaulu.subcast.di.scope.FavouritesScope
import com.kehtolaulu.subcast.presenters.FavouritesPresenter
import com.kehtolaulu.subcast.ui.FavouritesFragment
import dagger.Component

@FavouritesScope
@Component(dependencies = [AppComponent::class], modules = [FavouritesModule::class])
interface FavouritesComponent {
    fun inject(fragment: FavouritesFragment)

    fun providePresenter(): FavouritesPresenter
}