package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.PlayerModule
import com.kehtolaulu.subcast.di.scope.PlayerScope
import com.kehtolaulu.subcast.presenters.PlayerPresenter
import com.kehtolaulu.subcast.ui.PlayerActivity
import dagger.Component

@PlayerScope
@Component(dependencies = [AppComponent::class], modules = [PlayerModule::class])
interface PlayerComponent {

    fun inject(activity: PlayerActivity)

    fun providePresenter(): PlayerPresenter
}
