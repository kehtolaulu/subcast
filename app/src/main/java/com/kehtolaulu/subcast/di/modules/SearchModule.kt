package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.api.SearchApi
import com.kehtolaulu.subcast.di.scope.SearchScope
import com.kehtolaulu.subcast.presenters.SearchPresenter
import com.kehtolaulu.subcast.services.SearchService
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @SearchScope
    @Provides
    fun provideSearchRepository(api: SearchApi): SearchService =
        SearchService(api)

    @SearchScope
    @Provides
    fun providePresenter(service: SearchService): SearchPresenter = SearchPresenter(service)
}
