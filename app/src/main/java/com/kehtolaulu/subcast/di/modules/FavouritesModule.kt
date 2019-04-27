package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.api.SearchApi
import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.di.scope.FavouritesScope
import com.kehtolaulu.subcast.presenters.FavouritesPresenter
import com.kehtolaulu.subcast.presenters.SearchPresenter
import com.kehtolaulu.subcast.services.FavouritesService
import com.kehtolaulu.subcast.services.SearchService
import dagger.Module
import dagger.Provides

@Module
class FavouritesModule {
    @FavouritesScope
    @Provides
    fun provideFavouritesService(api: SubcastApi): FavouritesService =
        FavouritesService(api)

    @FavouritesScope
    @Provides
    fun providePresenter(service: FavouritesService): FavouritesPresenter = FavouritesPresenter(service)
}
