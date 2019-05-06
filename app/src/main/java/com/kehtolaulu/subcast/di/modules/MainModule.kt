package com.kehtolaulu.subcast.di.modules

import android.content.Context
import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.database.TokenDao
import com.kehtolaulu.subcast.di.scope.MainScope
import com.kehtolaulu.subcast.di.scope.PlayerScope
import com.kehtolaulu.subcast.services.EpisodesService
import com.kehtolaulu.subcast.services.TokenService
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @MainScope
    @Provides
    fun provideTokenService(tokenDao: TokenDao, context: Context): TokenService =
        TokenService(tokenDao, context)
}
