package com.kehtolaulu.subcast.di.modules

import com.kehtolaulu.subcast.di.scope.LoginScope
import com.kehtolaulu.subcast.presentation.feature.login.presenter.LoginPresenter
import com.kehtolaulu.subcast.data.interactor.AuthInteractor
import com.kehtolaulu.subcast.data.interactor.TokenInteractor
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

//    @LoginScope
//    @Provides
//    fun provideTokenService(tokenDao: TokenDao, context: Context): TokenInteractor =
//        TokenInteractor(tokenDao, context)
//
//    @LoginScope
//    @Provides
//    fun provideAuthService(subcastApi: SubcastApi): AuthInteractor =
//        AuthInteractor(subcastApi)

    @LoginScope
    @Provides
    fun providePresenter(
        tokenInteractor: TokenInteractor,
        authInteractor: AuthInteractor
    ): LoginPresenter =
        LoginPresenter(tokenInteractor, authInteractor)
}
