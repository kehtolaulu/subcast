package com.kehtolaulu.subcast.di.modules

import android.content.Context
import com.kehtolaulu.subcast.data.interactor.AuthInteractor
import com.kehtolaulu.subcast.data.interactor.TokenInteractor
import com.kehtolaulu.subcast.di.scope.LoginScope
import com.kehtolaulu.subcast.presentation.feature.login.presenter.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @LoginScope
    @Provides
    fun providePresenter(
        tokenInteractor: TokenInteractor,
        authInteractor: AuthInteractor,
        context: Context
    ): LoginPresenter =
        LoginPresenter(tokenInteractor, authInteractor, context)
}
