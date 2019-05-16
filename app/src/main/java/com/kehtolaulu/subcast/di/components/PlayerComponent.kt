package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.PlayerModule
import com.kehtolaulu.subcast.di.scope.PlayerScope
import com.kehtolaulu.subcast.presentation.feature.player.presenter.PlayerPresenter
import com.kehtolaulu.subcast.presentation.feature.player.activity.PlayerActivity
import dagger.Component

@PlayerScope
@Component(dependencies = [AppComponent::class], modules = [PlayerModule::class])
interface PlayerComponent {

    fun inject(activity: PlayerActivity)

    fun providePresenter(): PlayerPresenter
}
