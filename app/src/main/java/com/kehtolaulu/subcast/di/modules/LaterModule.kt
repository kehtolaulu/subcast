package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.di.scope.LaterScope
import com.kehtolaulu.subcast.presentation.feature.playlater.presenter.LaterPresenter
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import dagger.Module
import dagger.Provides

@Module
class LaterModule {
    @LaterScope
    @Provides
    fun providePresenter(interactor: EpisodesInteractor): LaterPresenter =
        LaterPresenter(interactor)
}
