package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.di.scope.PlayerScope
import com.kehtolaulu.subcast.presenters.PlayerPresenter
import com.kehtolaulu.subcast.services.EpisodesService
import dagger.Module
import dagger.Provides

@Module
class PlayerModule {

    @PlayerScope
    @Provides
    fun provideEpisodesService(dao: EpisodeDao, subcastApi: SubcastApi): EpisodesService =
        EpisodesService(dao, subcastApi)

    @PlayerScope
    @Provides
    fun providePresenter(service: EpisodesService): PlayerPresenter = PlayerPresenter(service)
}
