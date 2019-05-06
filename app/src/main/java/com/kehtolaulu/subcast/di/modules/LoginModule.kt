package com.kehtolaulu.subcast.di.modules

import android.content.Context
import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.TokenDao
import com.kehtolaulu.subcast.di.scope.LoginScope
import com.kehtolaulu.subcast.presenters.LoginPresenter
import com.kehtolaulu.subcast.services.AuthService
import com.kehtolaulu.subcast.services.TokenService
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @LoginScope
    @Provides
    fun provideTokenService(tokenDao: TokenDao, context: Context): TokenService =
        TokenService(tokenDao, context)

    @LoginScope
    @Provides
    fun provideAuthService(subcastApi: SubcastApi): AuthService =
        AuthService(subcastApi)

    @LoginScope
    @Provides
    fun providePresenter(
        tokenService: TokenService,
        authService: AuthService
    ): LoginPresenter =
        LoginPresenter(tokenService, authService)
}
