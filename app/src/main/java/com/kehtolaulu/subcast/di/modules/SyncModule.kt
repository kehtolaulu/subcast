package com.kehtolaulu.subcast.di.modules

import android.content.Context
import com.kehtolaulu.subcast.database.TokenDao
import com.kehtolaulu.subcast.di.scope.SyncScope
import com.kehtolaulu.subcast.presenters.SyncPresenter
import com.kehtolaulu.subcast.services.TokenService
import dagger.Module
import dagger.Provides

@Module
class SyncModule {
    @SyncScope
    @Provides
    fun provideTokenService(tokenDao: TokenDao, context: Context): TokenService =
        TokenService(tokenDao, context)

    @SyncScope
    @Provides
    fun provideSyncPresenter(tokenService: TokenService) : SyncPresenter = SyncPresenter(tokenService)
}
