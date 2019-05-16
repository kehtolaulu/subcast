package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.MainModule
import com.kehtolaulu.subcast.di.scope.MainScope
import com.kehtolaulu.subcast.data.interactor.TokenInteractor
import com.kehtolaulu.subcast.presentation.feature.main.activity.MainActivity
import dagger.Component

@MainScope
@Component(dependencies = [AppComponent::class], modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)

    fun provideService() : TokenInteractor
}
