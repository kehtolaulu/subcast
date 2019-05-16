package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.di.scope.PlayerScope
import com.kehtolaulu.subcast.presentation.feature.player.presenter.PlayerPresenter
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.presentation.service.PlayerServiceConnection
import dagger.Module
import dagger.Provides

@Module
class PlayerModule {

    @Provides
    fun provideServiceConnection(app: MyApplication): PlayerServiceConnection {
        return PlayerServiceConnection(app)
    }

    @PlayerScope
    @Provides
    fun providePresenter(interactor: EpisodesInteractor, serviceConnection: PlayerServiceConnection): PlayerPresenter =
        PlayerPresenter(interactor, serviceConnection)
}
