package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.di.scope.PlayerScope
import com.kehtolaulu.subcast.presenters.PlayerPresenter
import com.kehtolaulu.subcast.services.PlayerService
import dagger.Module
import dagger.Provides

@Module
class PlayerModule {

    @PlayerScope
    @Provides
    fun providePlayerService(dao: EpisodeDao): PlayerService = PlayerService(dao)

    @PlayerScope
    @Provides
    fun providePresenter(service: PlayerService): PlayerPresenter = PlayerPresenter(service)
}
