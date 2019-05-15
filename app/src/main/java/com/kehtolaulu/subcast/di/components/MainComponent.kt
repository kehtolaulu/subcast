package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.MainModule
import com.kehtolaulu.subcast.di.scope.MainScope
import com.kehtolaulu.subcast.services.TokenService
import com.kehtolaulu.subcast.ui.MainActivity
import dagger.Component

@MainScope
@Component(dependencies = [AppComponent::class], modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)

    fun provideService() : TokenService
}
