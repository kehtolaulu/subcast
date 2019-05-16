package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.LoginModule
import com.kehtolaulu.subcast.di.scope.LoginScope
import com.kehtolaulu.subcast.presentation.feature.login.presenter.LoginPresenter
import com.kehtolaulu.subcast.presentation.feature.login.activity.LoginFragment
import dagger.Component

@LoginScope
@Component(dependencies = [AppComponent::class], modules = [LoginModule::class])
interface LoginComponent {
    fun inject(fragment: LoginFragment)

    fun providePresenter(): LoginPresenter
}
